package com.tpp.threat_perception_platform.config;

import com.tpp.threat_perception_platform.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    // 创建BCryptPasswordEncoder注入容器
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 创建AuthenticationManager注入容器
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 解决 in a frame because it set 'X-Frame-Options' to 'deny'
                .headers(head->{
                    head.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
                    head.httpStrictTransportSecurity(HeadersConfigurer.HstsConfig::disable);
                })
                // CSRF禁用，因为不使用session
                .csrf(AbstractHttpConfigurer::disable)
                // 不通过Session获取SecurityContext
                .sessionManagement(sess->{
                    sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                // 过滤请求
                .authorizeHttpRequests(auth->{
                    // 对于登录接口 允许匿名访问
                    auth.requestMatchers("/user/login").anonymous();
                    // 退出登录接口
                    //.requestMatchers("/user/logout").permitAll()
                    // 所有页面放行 允许匿名访问
                    auth.requestMatchers("/page/**").anonymous();
                    auth.requestMatchers("/").anonymous();
                    // CSS/JS/IMG/LIB/FONTS放行
                    auth.requestMatchers("/css/**").anonymous();
                    auth.requestMatchers("/js/**").anonymous();
                    auth.requestMatchers("/img/**").anonymous();
                    auth.requestMatchers("/imgs/**").anonymous();
                    auth.requestMatchers("/lib/**").anonymous();
                    auth.requestMatchers("/fonts/**").anonymous();
                    auth.requestMatchers("/layui/**").anonymous();
                    // 除上面外的所有请求全部需要鉴权认证
                    auth.anyRequest().authenticated();
                });
        // 把token校验过滤器添加到过滤器链中
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
