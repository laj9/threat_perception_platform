package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【service】的数据库操作Mapper
 * @createDate 2024-06-12 20:33:38
 * @Entity pojo.Service
 */
public interface ServiceMapper {

    int deleteByPrimaryKey(Long id);

    int deleteByMac(String mac);

    void delete(@Param("ids") Integer[] ids);

    int insert(Service record);

    int insertSelective(Service record);

    int insertBatch(@Param("services") List<Service> services);

    Service selectByPrimaryKey(Long id);

    List<Service> selectAll(MyParam param);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);

}
