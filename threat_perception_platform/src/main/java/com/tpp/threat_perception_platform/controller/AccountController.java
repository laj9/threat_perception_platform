package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 获取账户列表
     * @param param
     * @return
     */
    @PostMapping("/account/list")
    public ResponseResult listAccount(MyParam param) {
        return accountService.getAllAccount(param);
    }

    /**
     * 删除账户操作
     * @param ids
     * @return
     */
    @PostMapping("/account/delete")
    public ResponseResult deleteHost(@RequestParam("ids[]") Integer[] ids){
        return accountService.deleteAccount(ids);
    }

}
