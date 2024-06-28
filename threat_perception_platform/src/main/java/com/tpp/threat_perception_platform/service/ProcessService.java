package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Process;
import com.tpp.threat_perception_platform.response.ResponseResult;

import java.util.List;

public interface ProcessService {
    // 将探测到的进程信息插入数据库
    int addProcess(List<Process> processes);

    // 获取所有账号信息
    ResponseResult getAllProcess(MyParam param);

    // 删除进程信息
    ResponseResult deleteProcess(Integer[] ids);
}
