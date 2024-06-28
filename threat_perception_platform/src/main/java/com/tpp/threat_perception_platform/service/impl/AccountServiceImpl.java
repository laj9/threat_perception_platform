package com.tpp.threat_perception_platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.AccountMapper;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Account;
import com.tpp.threat_perception_platform.pojo.Host;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 将探测到的账号信息入库
     * @param accounts
     * @return
     */
    @Override
    public int addAccount(List<Account> accounts) {
        // 1.将数据库中原有的数据删除，以免重复
        // 根据探测到的主机的mac地址，将该主机在数据库中已有的信息全部删除，以免重复
        // 获取mac地址
        String mac = accounts.get(0).getMac();
        // 删除数据
        accountMapper.deleteByMac(mac);

        // 2.将新数据入库
        return accountMapper.insertBatch(accounts);
    }

    /**
     * @param param
     * @return
     */
    @Override
    public ResponseResult getAllAccount(MyParam param) {
        // 1.进行分页
        PageHelper.startPage(param.getPage(), param.getLimit());

        // 2.获取账号列表
        List<Account> accountList = accountMapper.selectAll(param);

        // 3.获取分页信息
        PageInfo pageInfo = new PageInfo(accountList);

        // 4.返回数据
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @param ids
     * @return
     */
    public ResponseResult deleteAccount(Integer[] ids) {
        // 删除账号ID数组
        accountMapper.delete(ids);

        // 返回表示删除成功的结果对象
        // 返回表示删除成功的结果对象
        return new ResponseResult(0, "删除成功!");
    }

    @Override
    public List<Account> getAccountListByMac(String mac) {
        return accountMapper.selectByMac(mac);
    }

}
