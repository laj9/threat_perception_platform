package com.tpp.threat_perception_platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.BaseLineTaskMapper;
import com.tpp.threat_perception_platform.param.BaseLineTaskParam;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.BaseLineTask;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.BaseLineTaskService;
import com.tpp.threat_perception_platform.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseLineTaskServiceImpl implements BaseLineTaskService {

    @Autowired
    private BaseLineTaskMapper baseLineTaskMapper;

    @Autowired
    private RabbitmqService rabbitmqService;

    /**
     * 根据状态查询基线任务列表
     *
     * @param status
     * @return
     */
    @Override
    public List<BaseLineTask> selectBaseLineTaskByStatus(Integer status) {
        return baseLineTaskMapper.selectByStatus(status);
    }

    /**
     * 更新基线任务
     *
     * @param baseLineTask
     * @return
     */
    @Override
    public int updateBaseLineTaskByIdSelective(BaseLineTask baseLineTask) {
        return baseLineTaskMapper.updateByPrimaryKeySelective(baseLineTask);
    }

    /**
     * 查询基线任务列表
     *
     * @param myParam
     * @return
     */
    @Override
    public ResponseResult findBaseLineTaskList(MyParam myParam) {
        // 1. 获取分页信息
        PageHelper.startPage(myParam.getPage(), myParam.getLimit());

        // 2. 查询列表
        List<BaseLineTask> baseLineTasks = baseLineTaskMapper.selectAll(myParam);

        // 3. 进行分页
        PageInfo pageInfo = new PageInfo(baseLineTasks);

        // 4. 返回结果
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 删除基线任务
     *
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteBaseLineTask(Integer[] ids) {
        baseLineTaskMapper.delete(ids);
        return new ResponseResult(0, "删除成功");
    }

    /**
     * 编辑基线任务
     *
     * @param baseLineTask
     * @return
     */
    @Override
    public ResponseResult editBaseLineTask(BaseLineTask baseLineTask) {
        // 更新数据库
        baseLineTaskMapper.updateByPrimaryKeySelective(baseLineTask);
        // 返回结果
        return new ResponseResult(0, "编辑成功");
    }

    /**
     * 保存基线任务
     * @param baseLineTask
     * @return
     */
    @Override
    public ResponseResult saveBaseLineTask(BaseLineTask baseLineTask) {
        // 保存
        baseLineTaskMapper.insertSelective(baseLineTask);
        return new ResponseResult<>(0, "保存成功");
    }

    /**
     * 立即启动基线任务
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult startBaseLineTask(Integer id) {
        // 根据id查询任务
        BaseLineTask task = baseLineTaskMapper.selectById(Long.valueOf(id));

        // 立即启动该任务
        // 1. 向客户端发送指令，进行基线检测
        // 1.1. 获取baseLineTaskParam对象,并设置参数
        // 获取mac地址的列表
        String[] macList = task.getTaskHosts().split(",");
        // 向每台主机的消息接收队列发送消息
        for (String mac : macList) {
            // 创建BaseLineTaskParam对象承载数据
            BaseLineTaskParam baseLineTaskParam = new BaseLineTaskParam();
            // 设置MAC地址
            baseLineTaskParam.setMac(mac);

            // 4.2. 将ThreatParam对象转换为JSON字符串
            String data = JSON.toJSONString(baseLineTaskParam);

            // 4.3. 发送消息到rabbitmq中（消息队列为agent_mac地址_queue）
            String exchangeName = "agent_exchange";
            String routingKey = baseLineTaskParam.getMac().replace(":","");
            rabbitmqService.sendMessage(exchangeName, routingKey, data);
        }

        // 5. 更新任务状态为1（已执行）
        // 创建一个新对象承载数据（老对象数据多，减少内存占用）
        BaseLineTask baseLineTask = new BaseLineTask();
        baseLineTask.setId(task.getId());
        baseLineTask.setTaskStatus(1);
        baseLineTaskMapper.updateByPrimaryKeySelective(baseLineTask);

        return new ResponseResult(0, "启动成功,请稍后！");
    }
}
