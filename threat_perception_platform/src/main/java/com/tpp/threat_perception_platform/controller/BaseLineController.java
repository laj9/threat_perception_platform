package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.BaseLineTask;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.BaseLineResultService;
import com.tpp.threat_perception_platform.service.BaseLineTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseLineController {
    @Autowired
    private BaseLineTaskService baseLineTaskService;

    @Autowired
    private BaseLineResultService baseLineResultService;

    /**
     * 查询基线任务列表
     * @param param
     * @return
     */
    @PostMapping("/baseLineTask/list")
    public ResponseResult findBaseLineTaskList(MyParam param)
    {
        return baseLineTaskService.findBaseLineTaskList(param);
    }

    /**
     * 删除基线任务
     * @param ids
     * @return
     */
    @PostMapping("/baseLineTask/delete")
    public ResponseResult deleteBaseLineTask(@RequestParam("ids[]") Integer[] ids)
    {
        return baseLineTaskService.deleteBaseLineTask(ids);
    }

    /**
     * 编辑基线任务
     * @param baseLineTask
     * @return
     */
    @PostMapping("/baseLineTask/edit")
    public ResponseResult editBaseLineTask(@RequestBody BaseLineTask baseLineTask)
    {
        return baseLineTaskService.editBaseLineTask(baseLineTask);
    }

    /**
     * 保存基线任务
     * @param baseLineTask
     * @return
     */
    @PostMapping("/baseLineTask/save")
    public ResponseResult saveBaseLineTask(@RequestBody BaseLineTask baseLineTask)
    {
        return baseLineTaskService.saveBaseLineTask(baseLineTask);
    }

    /**
     * 启动基线任务
     * 根据id启动基线探测任务
     * @param id
     * @return
     */
    @PostMapping("/baseLineTask/confirm")
    public ResponseResult startBaseLineTask(@RequestParam("id") Integer id){
        return baseLineTaskService.startBaseLineTask(id);
    }

    /**
     * 查询基线结果列表
     * @param param
     * @return
     */
    @PostMapping("/baseLineResult/list")
    public ResponseResult findBaseLineResultList(MyParam param)
    {
        return baseLineResultService.findBaseLineResultList(param);
    }

    /**
     * 删除基线结果
     * @param ids
     * @return
     */
    @PostMapping("/baseLineResult/delete")
    public ResponseResult deleteBaseLineResult(@RequestParam("ids[]") Integer[] ids)
    {
        return baseLineResultService.deleteBaseLineResult(ids);
    }
}
