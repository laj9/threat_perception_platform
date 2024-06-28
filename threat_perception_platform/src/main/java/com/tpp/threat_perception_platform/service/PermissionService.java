package com.tpp.threat_perception_platform.service;
import com.tpp.threat_perception_platform.pojo.Permission;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.param.MyParam;

/**
 * 角色的业务逻辑接口
 * @create 2021-05-06 16:05
 */
public interface PermissionService {

    //列出用户列表
    ResponseResult listPermission(MyParam param);

    //添加用户
    ResponseResult addPermission(Permission permission);


    ResponseResult delete(Integer[] ids);

    ResponseResult editPermission(Permission permission);

}
