package com.tpp.threat_perception_platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.tpp.threat_perception_platform.pojo.LoginUser;
import com.tpp.threat_perception_platform.pojo.User;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.LoginServcie;
import com.tpp.threat_perception_platform.service.UserService;
import com.tpp.threat_perception_platform.utils.JwtUtil;
import com.tpp.threat_perception_platform.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class LoginUserImpl implements LoginServcie {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult<Object> login(User user) {
        // AuthenticationManager的authenticate方法进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPwd());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 如果认证没通过，给出对应提示
        if (Objects.isNull(authenticate)) {
            return new ResponseResult<Object>(1001, "认证失败！");
        }
        // 如果认证通过了，使用UUID(用户ID)生成JWT
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 把完整的用户信息存入redis, 有效期1个小时
        String uuid = JwtUtil.getUUID();
        String jwt = JwtUtil.createJWT(uuid);
        loginUser.setUuid(uuid);
        redisCache.setCacheObject("login_" + uuid, JSON.toJSONString(loginUser), 60 * 60, TimeUnit.SECONDS);

        // 更新登录时间
        // 防止更新到密码，将密码设置为空
        user.setUserPwd(null);
        user.setId(loginUser.getId());
        user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        userService.updateUser(user);

        // 把token响应给前端
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseResult<Object>(200, "登录成功！", map);
    }

    @Override
    public ResponseResult logout() {
        // 获取SecurityContextHolder中的用户ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String uuid = loginUser.getUuid();
        // 删除redis中的值
        redisCache.deleteObject("login_" + uuid);
        return new ResponseResult<Object>(200, "退出成功！");
    }


}

