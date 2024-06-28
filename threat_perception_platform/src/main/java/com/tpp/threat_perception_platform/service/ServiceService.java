package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Service;
import com.tpp.threat_perception_platform.response.ResponseResult;

import java.util.List;

public interface ServiceService {
    // 将探测到的服务信息插入数据库
    int addService(List<Service> services);

    // 获取所有服务信息
    ResponseResult getAllService(MyParam param);

    // 删除服务信息
    ResponseResult deleteService(Integer[] ids);
}
