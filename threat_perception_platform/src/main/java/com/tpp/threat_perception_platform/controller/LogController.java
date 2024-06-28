package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 获取日志列表
     * @param myParam
     * @return
     */
    @PostMapping("/log/list")
    public ResponseResult selectAllLogList(MyParam myParam) {
        return logService.selectAllLogList(myParam);
    }

    /**
     * 获取登录日志列表
     * @param myParam
     * @return
     */
    @PostMapping("/log/loginList")
    public ResponseResult selectLoginLog(MyParam myParam) {
        return logService.selectLoginLog(myParam);
    }

    /**
     * 获取账号日志列表
     * @param myParam
     * @return
     */
    @PostMapping("/log/accountList")
    public ResponseResult selectAccountLog(MyParam myParam) {
        return logService.selectAccountLog(myParam);
    }

    /**
     * 删除日志
     * @param ids
     * @return
     */
    @PostMapping("/log/delete")
    public ResponseResult deleteLog(@RequestParam("ids[]") Integer[] ids) {
        return logService.deleteLog(ids);
    }


}
