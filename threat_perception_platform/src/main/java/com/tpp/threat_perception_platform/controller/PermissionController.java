package com.tpp.threat_perception_platform.controller;


import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Permission;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.PermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 角色相关的接口
 */
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取角色列表
     * @return
     */
    @PostMapping("/permission/list")
    public ResponseResult permissionList(MyParam myParam)
    {
        return permissionService.listPermission(myParam);
    }

    /**
     * 添加角色
     *
     */
    @PostMapping("/permission/add")
    public ResponseResult permissionSave(@RequestBody Permission permission)
    {
        return permissionService.addPermission(permission);
    }

    /**
     * 删除角色
     *
     */
    @PostMapping("/permission/delete")
    public ResponseResult permissionDelete(@RequestParam("ids[]") Integer[] ids)
    {
        return permissionService.delete(ids);
    }

    @PostMapping("/permission/edit")
    public ResponseResult permissionEdit(@RequestBody Permission permission)
    {
        return permissionService.editPermission(permission);
    }
}
