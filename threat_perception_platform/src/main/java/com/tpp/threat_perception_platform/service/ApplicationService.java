package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Application;
import com.tpp.threat_perception_platform.response.ResponseResult;

import java.util.List;

public interface ApplicationService {
    // 将探测到的应用信息插入数据库
    int addApplication(List<Application> applications);

    // 获取所有应用信息
    ResponseResult getAllApplication(MyParam param);

    // 删除应用信息
    ResponseResult deleteApplication(Integer[] ids);
}
