package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Host;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【host】的数据库操作Mapper
 * @createDate 2024-06-07 16:44:12
 * @Entity pojo.Host
 */
public interface HostMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Host record);

    int insertSelective(Host record);

    Host selectByPrimaryKey(Long id);
    Host selectByMac(String mac);

    List<Host> selectAll(MyParam myParam);

    List<Host> selectAllHosts();

    List<Host> selectHostTypes();

    List<Host> findAll2();

    int updateByPrimaryKeySelective(Host record);

    int updateByPrimaryKey(Host record);

    int updateByMacSelective(Host record);

}
