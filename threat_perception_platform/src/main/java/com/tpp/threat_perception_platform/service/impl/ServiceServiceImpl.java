package com.tpp.threat_perception_platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.ServiceMapper;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Account;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceMapper serviceMapper;
    /**
     * 将探测到的服务信息入库
     * @param services
     * @return
     */
    @Override
    public int addService(List<com.tpp.threat_perception_platform.pojo.Service> services) {
        // 1.将数据库中原有的数据删除，以免重复
        // 根据探测到的主机的mac地址，将该服务在数据库中已有的信息全部删除，以免重复
        // 获取mac地址
        String mac = services.get(0).getMac();
        // 删除数据
        serviceMapper.deleteByMac(mac);

        // 2.将新数据入库
        return serviceMapper.insertBatch(services);
    }

    /**
     * @param param
     * @return
     */
    @Override
    public ResponseResult getAllService(MyParam param) {
        // 1.进行分页
        PageHelper.startPage(param.getPage(), param.getLimit());

        // 2.获取服务列表
        List<com.tpp.threat_perception_platform.pojo.Service> serviceList = serviceMapper.selectAll(param);

        // 3.获取分页信息
        PageInfo pageInfo = new PageInfo(serviceList);

        // 4.返回数据
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 删除服务信息
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteService(Integer[] ids) {
        // 待删除的ID数组
        serviceMapper.delete(ids);

        // 返回表示删除成功的结果对象
        // 返回表示删除成功的结果对象
        return new ResponseResult(0, "删除成功!");
    }
}
