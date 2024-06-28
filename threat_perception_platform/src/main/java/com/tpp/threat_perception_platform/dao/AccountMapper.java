package com.tpp.threat_perception_platform.dao;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LIUAIJING
 * @description 针对表【account】的数据库操作Mapper
 * @createDate 2024-06-12 15:06:38
 * @Entity pojo.Account
 */
public interface AccountMapper {

    int deleteByPrimaryKey(Long id);

    /**
     * 删除多条数据
     * @param ids
     */
    void delete(@Param("ids") Integer[] ids);

    int insert(Account record);

    int insertSelective(Account record);

    int insertBatch(@Param("accounts") List<Account> accounts);

    Account selectByPrimaryKey(Long id);

    List<Account> selectAll(MyParam param);

    List<Account> selectByMac(String mac);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int deleteByMac(String mac);

}
