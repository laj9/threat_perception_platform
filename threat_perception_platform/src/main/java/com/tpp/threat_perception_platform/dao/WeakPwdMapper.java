package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.WeakPwd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【weak_pwd】的数据库操作Mapper
 * @createDate 2024-06-18 18:36:28
 * @Entity pojo.WeakPwd
 */
public interface WeakPwdMapper {

    int deleteByPrimaryKey(Long id);

    int deleteByMac(String mac);

    int insert(WeakPwd record);

    int insertBatch(@Param("weakPwds") List<WeakPwd> weakPwds);

    int insertSelective(WeakPwd record);

    WeakPwd selectByPrimaryKey(Long id);

    List<WeakPwd> selectAll(MyParam myParam);

    int updateByPrimaryKeySelective(WeakPwd record);

    int updateByPrimaryKey(WeakPwd record);

    int delete(@Param("ids") Integer[] ids);


}
