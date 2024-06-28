package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【log】的数据库操作Mapper
 * @createDate 2024-06-20 19:23:10
 * @Entity pojo.Log
 */
public interface LogMapper {

    int deleteByPrimaryKey(Long id);

    int delete(@Param("ids") Integer[] ids);

    int insert(Log record);

    int insertSelective(Log record);

    int insertBatch(@Param("logs") List<Log> logs);

    Log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectAll(MyParam param);

    List<Log> selectLoginLog(MyParam param);

    List<Log> selectAccountLog(MyParam param);

    List<Log> select(@Param("ids") Integer[] ids);

    List<Log> findAll();

}
