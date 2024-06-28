package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.WeakPwd;
import com.tpp.threat_perception_platform.response.ResponseResult;

import java.util.List;

public interface WeakPwdService {
    // 将探测到的弱口令信息插入数据库中
    int addWeakPwd(List<WeakPwd> weakPwds);

    // 查询弱密码信息列表
    ResponseResult getAllWeakPwd(MyParam param);

    // 删除弱密码信息
    ResponseResult deleteWeakPwd(Integer[] ids);
}
