package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Role;
import com.tpp.threat_perception_platform.response.ResponseResult;

public interface RoleService {

    // 获取角色列表
    ResponseResult getRoleList(MyParam param);

    // 添加用户
    ResponseResult addRole(Role role);

    // 修改角色信息
    ResponseResult editRole(Role role);

    // 删除角色
    ResponseResult deleteRole(Integer[] ids);
}
