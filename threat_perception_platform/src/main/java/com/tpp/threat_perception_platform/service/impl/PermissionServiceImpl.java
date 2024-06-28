package com.tpp.threat_perception_platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.PermissionMapper;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Permission;
import com.tpp.threat_perception_platform.pojo.User;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public ResponseResult listPermission(MyParam param) {
        //处理分页的逻辑
        PageHelper.startPage(param.getPage(), param.getLimit());

        //业务逻辑
        List<Permission> permissionList = permissionMapper.findAll(param);

        //构架pageInfo
        PageInfo<Permission> pageInfo = new PageInfo<>(permissionList);

        return new ResponseResult<>(pageInfo.getTotal(), pageInfo.getList());
    }


    @Override
    public ResponseResult addPermission(Permission permission) {
        // 先查询 是否有角色
        Permission db_permission = permissionMapper.selectByPermName(permission.getPermName());
        if ( db_permission!= null){
            return new ResponseResult<>(1003, "角色已存在！");
        }
        //角色未重复则添加角色
        permission.setPermName(permission.getPermName());
        permission.setPermDesc(permission.getPermDesc());
        permissionMapper.insertSelective(permission);

        return new ResponseResult<>(0, "添加成功！");
    }

    /**
     * 删除角色
     * @param ids
     * @return
     */
    @Override
    public ResponseResult delete(Integer[] ids) {
        permissionMapper.delete(ids);
        return new ResponseResult<>(0, "删除成功！");
    }

    @Override
    public ResponseResult editPermission(Permission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
        return new ResponseResult<>(0, "修改成功！");
    }
}
