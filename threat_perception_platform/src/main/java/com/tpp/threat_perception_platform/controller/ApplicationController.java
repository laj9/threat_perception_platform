package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    /**
     * 获取所有应用列表
     * @param param
     * @return
     */
    @PostMapping("/application/list")
    public ResponseResult listApplication(MyParam param) {
        return applicationService.getAllApplication(param);
    }

    /**
     * 删除应用
     * @param ids
     * @return
     */
    @PostMapping("/application/delete")
    public ResponseResult deleteApplication(@RequestParam("ids[]") Integer[] ids) {
        return applicationService.deleteApplication(ids);
    }

}
