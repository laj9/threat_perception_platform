package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Application;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【application】的数据库操作Mapper
 * @createDate 2024-06-13 11:17:32
 * @Entity pojo.Application
 */
public interface ApplicationMapper {

    int deleteByPrimaryKey(Long id);

    void delete(@Param("ids") Integer[] ids);

    int insert(Application record);

    int insertSelective(Application record);

    int insertBatch(@Param("applications") List<Application> applications);

    Application selectByPrimaryKey(Long id);

    List<Application> selectAll(MyParam myParam);

    int deleteByMac(String mac);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

}
