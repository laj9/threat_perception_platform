package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2024-05-20 16:03:56
 * @Entity com.tpp.threat_perception_platform.pojo.User
 */
public interface UserMapper {

    int deleteByPrimaryKey(Long id);
    /**
     * 删除数据
     * @param ids
     */
    void delete(@Param("ids") Integer[] ids);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    /**
     * 查询所有
     * @param param
     * @return
     */
    List<User> findAll(@Param("param") MyParam param);

    /**
     * 根据用户名查询用户
     * @return
     */
    User selectByUserName(@Param("userName") String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
