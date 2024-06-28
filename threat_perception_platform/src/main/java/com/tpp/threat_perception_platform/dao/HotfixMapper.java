package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Account;
import com.tpp.threat_perception_platform.pojo.Hotfix;
import com.tpp.threat_perception_platform.response.ResponseResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【hotfix】的数据库操作Mapper
 * @createDate 2024-06-14 11:28:21
 * @Entity pojo.Hotfix
 */
public interface HotfixMapper {

    int deleteByPrimaryKey(Long id);

    int deleteByMac(String mac);

    void delete(@Param("ids") Integer[] ids);

    int insert(Hotfix record);

    int insertSelective(Hotfix record);

    int insertBatch(@Param("hotfixes") List<Hotfix> hotfixes);

    Hotfix selectByPrimaryKey(Long id);

    List<Hotfix> selectAll(MyParam myParam);

    int updateByPrimaryKeySelective(Hotfix record);

    int updateByPrimaryKey(Hotfix record);

}
