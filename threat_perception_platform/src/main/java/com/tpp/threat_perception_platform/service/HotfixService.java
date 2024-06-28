package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Hotfix;
import com.tpp.threat_perception_platform.response.ResponseResult;

import java.util.List;

public interface HotfixService {
    // 将探测到的补丁信息插入数据库中
    int addHotfix(List<Hotfix> hotfixes);

    // 查询补丁信息列表
    ResponseResult getAllHotfix(MyParam param);

    // 删除补丁信息
    ResponseResult deleteHotfix(Integer[] ids);
}
