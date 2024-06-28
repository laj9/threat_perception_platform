package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Log;
import com.tpp.threat_perception_platform.response.ResponseResult;

import java.util.List;

public interface LogService {
    /**
     * 将客户端传递来的数据存入数据库中
     */
    int addLogList(List<Log> logList);
    int addLog(Log log);

    /**
     * 展示所有列表
     */
    ResponseResult selectAllLogList(MyParam myParam);

    /**
     * 登录日志
     */
    ResponseResult selectLoginLog(MyParam myParam);

    /**
     * 账号日志
     */
    ResponseResult selectAccountLog(MyParam myParam);

    /**
     * 删除日志
     */
    ResponseResult deleteLog(Integer[] ids);

    /**
     * 返回所有数据的列表，用于饼图的统计
     */
    ResponseResult findAllLogList();

}
