package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.pojo.User;
import com.tpp.threat_perception_platform.response.ResponseResult;

/**
 * service接口
 */
public interface LoginServcie {
    /**
     * 登录
     * @param user 用户信息
     * @return ResponseResult 响应结果
     */
    ResponseResult login(User user);
    ResponseResult logout();

}
