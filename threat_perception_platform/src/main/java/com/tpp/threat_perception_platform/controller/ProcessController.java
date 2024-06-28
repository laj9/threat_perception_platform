package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {

    @Autowired
    private ProcessService processService;

    /**
     * 获取进程列表
     * @param param
     * @return
     */
    @PostMapping("/process/list")
    public ResponseResult listProcess(MyParam param) {
        return processService.getAllProcess(param);
    }

    @PostMapping("/process/delete")
    public ResponseResult deleteProcess(@RequestParam("ids[]") Integer[] ids) {
        return processService.deleteProcess(ids);
    }

}
