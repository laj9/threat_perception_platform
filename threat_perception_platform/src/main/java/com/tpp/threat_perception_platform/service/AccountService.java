package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Account;
import com.tpp.threat_perception_platform.response.ResponseResult;

import java.util.List;

public interface AccountService {

    // 将探测到的账号信息插入数据库中
    int addAccount(List<Account> accounts);

    // 获取所有账号信息
    ResponseResult getAllAccount(MyParam param);

    // 删除账号信息
    ResponseResult deleteAccount(Integer[] ids);

    // 根据账号的MAC地址获取所有的相关账号信息
    List<Account> getAccountListByMac(String mac);
}
