package com.tpp.threat_perception_platform.service.impl;


import com.tpp.threat_perception_platform.dao.UserMapper;
import com.tpp.threat_perception_platform.pojo.LoginUser;
import com.tpp.threat_perception_platform.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 认证
 *
 * @Author zwp
 * @Date 2022/9/12 16:53
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户数据
        User db_user = userMapper.selectByUserName(username);
        if (db_user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 判断状态
        if (db_user.getUserStatus() == 2) {
            throw new RuntimeException("用户被冻结！请联系超级管理员！");
        }
        // 这个地方还要获取权限信息

        // 把数据封装成LoginUser对象返回
        return new LoginUser(db_user.getUserName(), db_user.getUserPwd(), db_user.getId());
    }
}

