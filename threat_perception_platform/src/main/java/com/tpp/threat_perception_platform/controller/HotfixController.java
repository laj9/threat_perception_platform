package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.HotfixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotfixController {

    @Autowired
    private HotfixService hotfixService;

    /**
     * 获取热补丁列表
     * @param param
     * @return
     */
    @PostMapping("hotfix/list")
    public ResponseResult hotfixList(MyParam param) {
        return hotfixService.getAllHotfix(param);
    }

    @PostMapping("hotfix/delete")
    public ResponseResult hotfixDelete(@RequestParam("ids[]") Integer[] ids) {
        return hotfixService.deleteHotfix(ids);
    }
}
