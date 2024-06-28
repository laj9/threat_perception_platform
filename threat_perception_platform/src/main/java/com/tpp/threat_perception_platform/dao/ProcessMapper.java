package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Process;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【process】的数据库操作Mapper
 * @createDate 2024-06-12 20:55:21
 * @Entity pojo.Process
 */
public interface ProcessMapper {

    int deleteByPrimaryKey(Long id);

    int deleteByMac(String mac);

    void delete(@Param("ids") Integer[] ids);

    int insert(Process record);

    int insertSelective(Process record);

    int insertBatch(@Param("processes") List<Process> processes);

    Process selectByPrimaryKey(Long id);

    List<Process> selectAll(MyParam myParam);

    int updateByPrimaryKeySelective(Process record);

    int updateByPrimaryKey(Process record);

}
