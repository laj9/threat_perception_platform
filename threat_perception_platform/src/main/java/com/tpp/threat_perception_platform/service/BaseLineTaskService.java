package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.BaseLineTask;
import com.tpp.threat_perception_platform.response.ResponseResult;

import java.util.List;

public interface BaseLineTaskService {

    /**
     * 根据状态查询基线任务列表
     * @return
     */
    List<BaseLineTask> selectBaseLineTaskByStatus(Integer status);

    /**
     * 更新基线任务(更新状态)
     * @param baseLineTask
     * @return
     */
    int updateBaseLineTaskByIdSelective(BaseLineTask baseLineTask);

    /**
     * 查询基线任务列表
     * @param myParam
     * @return
     */
    ResponseResult findBaseLineTaskList(MyParam myParam);

    /**
     * 删除基线任务
     * @param ids
     * @return
     */
    ResponseResult deleteBaseLineTask(Integer[] ids);

    /**
     * 编辑基线任务
     * @param baseLineTask
     * @return
     */
    ResponseResult editBaseLineTask(BaseLineTask baseLineTask);

    /**
     * 保存基线任务
     * @param baseLineTask
     * @return
     */
    ResponseResult saveBaseLineTask(BaseLineTask baseLineTask);

    /**
     * 立即启动基线任务
     * @param id
     * @return
     */
    ResponseResult startBaseLineTask(Integer id);
}
