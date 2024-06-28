package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.BaseLineTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【base_line_task】的数据库操作Mapper
 * @createDate 2024-06-21 10:31:14
 * @Entity pojo.BaseLineTask
 */
public interface BaseLineTaskMapper {

    int deleteByPrimaryKey(Long id);

    int delete(@Param("ids") Integer[] ids);


    int insert(BaseLineTask record);

    int insertSelective(BaseLineTask record);

    BaseLineTask selectByPrimaryKey(Long id);

    List<BaseLineTask> selectByStatus(Integer status);

    List<BaseLineTask> selectAll(MyParam myparam);

    BaseLineTask selectById(Long id);

    int updateByPrimaryKeySelective(BaseLineTask record);

    int updateByPrimaryKey(BaseLineTask record);

}
