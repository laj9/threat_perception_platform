package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.AssetsParam;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.param.ThreatParam;
import com.tpp.threat_perception_platform.pojo.Host;
import com.tpp.threat_perception_platform.response.ResponseResult;

public interface HostService {

    // 主机信息入库
    int pushHost(Host host);

    // 查询主机列表
    ResponseResult findHostList(MyParam param);

    // 查询所有主机列表（不做分页）
    ResponseResult findHostList();

    // 删除角色
    ResponseResult deleteHost(Integer[] ids);

    // 心跳消息（根据mac地址确定主机，更新主机信息，status == 1）
    int heartbeat(Host host);

    // 资产探测的逻辑处理模块
    ResponseResult assets(AssetsParam param);

    // 威胁探测的逻辑处理模块
    ResponseResult threat(ThreatParam param);

    // 查询所有主机的类型返回前端，统计到饼状图中
    ResponseResult findHostTypes();

    ResponseResult findAll2();
}
