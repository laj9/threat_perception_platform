package com.tpp.threat_perception_platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.AppVulnerabilityMapper;
import com.tpp.threat_perception_platform.dao.HostMapper;
import com.tpp.threat_perception_platform.dao.VulnerabilityMapper;
import com.tpp.threat_perception_platform.param.AssetsParam;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.param.ThreatParam;
import com.tpp.threat_perception_platform.pojo.Account;
import com.tpp.threat_perception_platform.pojo.AppVulnerability;
import com.tpp.threat_perception_platform.pojo.Host;
import com.tpp.threat_perception_platform.pojo.Vulnerability;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {
    @Autowired
    private HostMapper hostMapper;

    @Autowired
    private RabbitmqService rabbitmqService;

    @Autowired
    private VulnerabilityService vulnerabilityService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AppVulnerabilityService appVulnerabilityService;

    /**
     * 推送主机信息到数据库中：更新和插入
     * @param host
     * @return
     */
    @Override
    public int pushHost(Host host) {
        // 1.先查询主机是否存在
        // 通过查询mac地址（mac地址唯一确定一个主机）
        Host db_host = hostMapper.selectByMac(host.getMac());

        if (db_host == null) {
            // 2.主机信息不存在
            // 插入到数据库中
            // 并创建一个专有队列agent_mac地址_queue的队列，用于平台下发指令到客户端

            // 主机上线时，动态地创建rabbitmq队列
            String exchangeName = "agent_exchange";
            String mac = host.getMac().replace(":","");
            String routingKey = mac;
            String queueName = "agent_" + mac + "_queue";
            rabbitmqService.createAgentQueue(exchangeName, queueName, routingKey);

            // 将主机信息插入数据库中
            // 设置状态
            host.setStatus(1);
            // 设置创建时间
            host.setCreateTime(new java.util.Date());

            return hostMapper.insertSelective(host);
        } else {
            // 3.主机信息存在，更新
            // 设置主机id
            host.setHostId(db_host.getHostId());
            // 设置更新时间
            host.setUpdateTime(new java.util.Date());

            return hostMapper.updateByPrimaryKeySelective(host);
        }
    }

    /**
     * 查询主机列表（包含搜索和分页）
     * @param param
     * @return
     */
    @Override
    public ResponseResult findHostList(MyParam param) {
        // 1.进行分页
        PageHelper.startPage(param.getPage(), param.getLimit());

        // 2.获取主机列表
        List<Host> hostList = hostMapper.selectAll(param);

        // 更新主机状态(遍历每一个主机，更新状态)
        for (Host db_host : hostList) {
            // 当前时间 - 上次更新时间 > 4s ? 离线: 在线
            if (new java.util.Date().getTime() - db_host.getUpdateTime().getTime() > 4000) {
                // 离线
                db_host.setStatus(0);
                // 更新数据库中的状态
                hostMapper.updateByPrimaryKeySelective(db_host);
            }
        }

        // 3.获取分页信息
        PageInfo pageInfo = new PageInfo(hostList);

        // 4.返回数据
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 获取所有主机列表（不做分页）
     * @return
     */
    @Override
    public ResponseResult findHostList() {
        List<Host> hostList = hostMapper.selectAllHosts();
        return new ResponseResult(0, hostList);
    }


    /**
     * 删除主机方法
     *
     * @param ids 主机ID数组，表示要删除的主机的ID集合
     * @return ResponseResult 对象，包含操作结果的状态码和消息
     *
     * 方法首先遍历输入的主机ID数组，对每个ID进行如下操作：
     * 1. 校验该ID对应的主机是否存在，如果不存在，则返回错误消息和状态码400；
     * 2. 如果存在，则调用hostMapper的deleteByPrimaryKey方法删除该主机。
     * 最后，无论删除操作是否成功，都会返回一个表示删除成功的结果对象。
     */
    @Override
    public ResponseResult deleteHost(Integer[] ids) {
        // 遍历待删除的主机ID数组
        // 遍历删除
        for (Integer id : ids) {
            // 校验当前ID对应的主机是否存在
            // 数据校验，确保要删除的数据是存在的
            if (hostMapper.selectByPrimaryKey(id.longValue()) == null) {
                // 如果主机不存在，则返回错误消息和状态码400
                // 如果角色不存在，返回错误消息和状态码400
                return new ResponseResult(400, "信息不存在");
            } else {
                // 如果主机存在，则删除该主机
                hostMapper.deleteByPrimaryKey(id.longValue());
            }
        }
        // 返回表示删除成功的结果对象
        // 返回表示删除成功的结果对象
        return new ResponseResult(0, "删除成功!");
    }

    /**
     * 主机心跳检测机制的逻辑实现模块
     * @param host
     * @return
     */
    @Override
    public int heartbeat(Host host) {
        // 获取当前时间
        host.setUpdateTime(new java.util.Date());

        // 更新主机信息(status = 1, update_time = 当前时间)
        return hostMapper.updateByMacSelective(host);
    }

    /**
     * 资产探测的逻辑处理模块
     * @param assetsParam
     * @return
     */
    @Override
    public ResponseResult assets(AssetsParam assetsParam) {
        // 1.判断主机是否在线
        // 获取主机
        Host db_host = hostMapper.selectByMac(assetsParam.getMac());
        // 当前时间 - db_host的update_time > 4s ? 离线:在线
        long sub = System.currentTimeMillis() - db_host.getUpdateTime().getTime();
        if(sub > 4000){
            // 差值大于4s,说明主机离线
            // 更新状态信息
            Host host = new Host();
            host.setStatus(0);
            host.setMac(assetsParam.getMac());
            hostMapper.updateByMacSelective(host);

            // 返回离线信息
            return new ResponseResult(400, "主机离线！请重新上线！");
        }

        // 2.将AssetsParm对象转换为JSON字符串
        String data = JSON.toJSONString(assetsParam);

        // 3.发送消息到rabbitmq中（消息队列为agent_mac地址_queue）
        String exchangeName = "agent_exchange";
        String mac = assetsParam.getMac().replace(":","");
        String routingKey = mac;
        rabbitmqService.sendMessage(exchangeName, routingKey, data);

        // 4. 返回发送成功
        return new ResponseResult<>(0, "发送成功，请等待探测结果！");
    }

    /**
     * 威胁探测的逻辑处理模块
     * @param  threatParam
     * @return
     */
    @Override
    public ResponseResult threat(ThreatParam threatParam) {
        // 1.判断主机是否在线
        // 获取主机
        Host db_host = hostMapper.selectByMac(threatParam.getMac());
        // 当前时间 - db_host的update_time > 4s ? 离线:在线
        long sub = System.currentTimeMillis() - db_host.getUpdateTime().getTime();
        if(sub > 4000){
            // 差值大于4s,说明主机离线
            // 更新状态信息
            Host host = new Host();
            host.setStatus(0);
            host.setMac(threatParam.getMac());
            hostMapper.updateByMacSelective(host);

            // 返回离线信息
            return new ResponseResult(400, "主机离线！请重新上线！");
        }
        // 如果threatParam中的vulnerability参数为1，说明进行漏洞探测，需要将漏洞数据库传到客户端去
        if(threatParam.getVulnerability() == 1){
            // 查询漏洞数据列表
            List<Vulnerability> vulnerabilityList = vulnerabilityService.findAll();
            // 将数据列表传入threatParam对象中
            threatParam.setVulnerabilities(vulnerabilityList);
        }

        // 如果threatParam中的weekPassword参数为1，说明进行弱口令探测(系统账号弱口令探测和系统服务弱口令探测)，需要将用户的账号数据库和服务数据库传到客户端去，进行弱口令测试
        if(threatParam.getWeakPassword() == 1)
        {
            // 根据主机的MAC地址查询主机上的账号信息
            List<Account> accountList = accountService.getAccountListByMac(threatParam.getMac());

            // 将数据列表传入threatParam对象中
            threatParam.setAccounts(accountList);
        }

        // 如果threatParam中的application参数为1，说明进行应用风险探测，需要将应用漏洞数据库传到客户端去，进行应用风险测试
        if(threatParam.getApplication() == 1)
        {
            // 查询应用漏洞数据列表
            List<AppVulnerability> appVulnerabilityList = appVulnerabilityService.findAppVulnerabilityList();

            // 将数据列表传入threatParam对象中
            threatParam.setAppVulnerabilities(appVulnerabilityList);
        }

        // 2.将ThreatParam对象转换为JSON字符串
        String data = JSON.toJSONString(threatParam);

        // 3.发送消息到rabbitmq中（消息队列为agent_mac地址_queue）
        String exchangeName = "agent_exchange";
        String mac = threatParam.getMac().replace(":","");
        String routingKey = mac;
        rabbitmqService.sendMessage(exchangeName, routingKey, data);

        // 4. 返回发送成功
        return new ResponseResult<>(0, "发送成功，请等待探测结果！");
    }

    /**
     * 查询所有主机的类型返回前端，统计到饼状图中
     * @return
     */
    @Override
    public ResponseResult findHostTypes() {
        return new ResponseResult<>(0, hostMapper.selectHostTypes());
    }

    @Override
    public ResponseResult findAll2() {
        List<Host> hosts =  hostMapper.findAll2();
        return new ResponseResult(0, hosts);
    }

}
