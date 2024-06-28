package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    /**
     * 通过POST请求获取服务列表。
     *
     * 此方法旨在处理客户端通过POST方式发送的请求，以获取服务列表。它接受一个MyParam对象作为参数，
     * 使用该参数来筛选或排序服务列表。返回一个ResponseResult对象，其中包含请求的结果。
     *
     * @param param 包含查询服务列表所需参数的对象，例如分页信息、排序条件等。
     * @return ResponseResult 对象，其中包含查询服务列表的结果。结果可能包括服务的详细信息列表，
     *         以及任何相关的错误或成功消息。
     */
    @PostMapping("/service/list")
    public ResponseResult listService(MyParam param) {
        return serviceService.getAllService(param);
    }

    @PostMapping("/service/delete")
    public ResponseResult deleteService(@RequestParam("ids[]") Integer[] ids) {
        return serviceService.deleteService(ids);
    }
}
