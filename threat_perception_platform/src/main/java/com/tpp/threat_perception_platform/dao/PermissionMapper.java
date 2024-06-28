package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Permission;
import com.tpp.threat_perception_platform.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author LiuAijing
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2024-06-24 14:23:28
* @Entity pojo.Permission
*/
public interface PermissionMapper {

    int deleteByPrimaryKey(Long id);

    void delete(@Param("ids") Integer[] ids);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    Permission selectByPermName(@Param("permName") String permName);

    List<Permission> findAll(@Param("param") MyParam param);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

}
