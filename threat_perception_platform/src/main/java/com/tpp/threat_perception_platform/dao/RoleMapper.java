package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Role;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【role】的数据库操作Mapper
 * @createDate 2024-06-05 11:31:14
 * @Entity pojo.Role
 */
public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    Role selectByRoleName(String roleName);

    List<Role> selectAll(MyParam myParam);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

}
