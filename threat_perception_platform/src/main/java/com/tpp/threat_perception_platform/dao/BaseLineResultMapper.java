package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.BaseLineResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【base_line_result】的数据库操作Mapper
 * @createDate 2024-06-22 11:42:26
 * @Entity pojo.BaseLineResult
 */
public interface BaseLineResultMapper {

    int deleteByPrimaryKey(Long id);

    int deleteByMac(String mac);

    int delete(@Param("ids") Integer[] ids);

    int insert(BaseLineResult record);

    int insertSelective(BaseLineResult record);

    int insertBatch(@Param("baseLineResults") List<BaseLineResult> baseLineResults);

    BaseLineResult selectByPrimaryKey(Long id);

    List<BaseLineResult> selectAll(MyParam myParam);

    List<BaseLineResult> findAll();

    int updateByPrimaryKeySelective(BaseLineResult record);

    int updateByPrimaryKey(BaseLineResult record);

}
