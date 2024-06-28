package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.pojo.User;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    LoginServcie loginServcie;


    /**
     * 登录接口
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginServcie.login(user);
    }

    /**
     * 登出接口
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/logout")
    public ResponseResult logout() {
        // 登出
        return loginServcie.logout();
    }
}
