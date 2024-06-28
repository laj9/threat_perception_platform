package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.WeakPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeakPwdController {

    @Autowired
    private WeakPwdService weakPwdService;

    /**
     * 弱口令列表
     * @param param
     * @return
     */
    @PostMapping("/weakPwd/list")
    public ResponseResult weakPwdList(MyParam param) {
        return weakPwdService.getAllWeakPwd(param);
    }

    /**
     * 删除弱口令
     * @param ids
     * @return
     */
    @PostMapping("/weakPwd/delete")
    public ResponseResult deleteWeakPwd(@RequestParam("ids[]") Integer[] ids) {
        System.out.println(ids);
        return weakPwdService.deleteWeakPwd(ids);
    }

}
