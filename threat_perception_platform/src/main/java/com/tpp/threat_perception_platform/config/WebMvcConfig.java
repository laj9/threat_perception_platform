package com.tpp.threat_perception_platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 主页
        registry.addViewController("/page/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        // 控制台
        registry.addViewController("/page/console").setViewName("console");
        // 登录页面
        registry.addViewController("/page/login").setViewName("login");
        // 修改密码
        registry.addViewController("/page/user/modifyPwd").setViewName("user/modifyPwd");
        // 用户相关页面
        registry.addViewController("/page/user/list").setViewName("user/list");
        registry.addViewController("/page/user/edit").setViewName("user/edit");
        registry.addViewController("/page/user/add").setViewName("user/add");

        // 角色相关页面
        registry.addViewController("/page/role/list").setViewName("role/list");
        registry.addViewController("/page/role/add").setViewName("role/add");
        registry.addViewController("/page/role/edit").setViewName("role/edit");

        // 资产相关页面
        // 展示主机列表
        registry.addViewController("/page/host/list").setViewName("host/list");
        // 主机详情
        registry.addViewController("/page/host/detail").setViewName("host/detail");
        // 资产探测
        registry.addViewController("/page/host/assets").setViewName("host/assets");
        // 威胁探测
        registry.addViewController("/page/host/threat").setViewName("host/threat");
        // 探测到的账号信息
        registry.addViewController("/page/account/list").setViewName("host/account");
        // 账号信息详情
        registry.addViewController("/page/account/detail").setViewName("host/accountDetail");
        // 探测到的服务信息
        registry.addViewController("/page/service/list").setViewName("host/service");
        // 服务信息详情
        registry.addViewController("/page/service/detail").setViewName("host/serviceDetail");
        // 探测到的进程信息
        registry.addViewController("/page/process/list").setViewName("host/process");
        // 进程信息详情
        registry.addViewController("/page/process/detail").setViewName("host/processDetail");
        // 探测到的应用信息
        registry.addViewController("/page/application/list").setViewName("host/application");
        // 应用信息详情
        registry.addViewController("/page/application/detail").setViewName("host/applicationDetail");

        // 威胁探测相关探测
        // 探测到的热补丁信息
        registry.addViewController("/page/hotfix/list").setViewName("host/hotfix");
        // 热补丁详情信息
        registry.addViewController("/page/hotfix/detail").setViewName("host/hotfixDetail");
        // 漏洞库信息
        registry.addViewController("/page/resource/vulnerability").setViewName("host/vulnerability");
        // 漏洞库详细信息
        registry.addViewController("/page/vulnerability/detail").setViewName("host/vulnerabilityDetail");
        // 添加漏洞信息
        registry.addViewController("/page/vulnerability/add").setViewName("host/vulnerabilityAdd");
        // 弱口令信息
        registry.addViewController("/page/weakPwd/list").setViewName("host/weakPwd");
        // 弱口令详细信息
        registry.addViewController("/page/weakPwd/detail").setViewName("host/weakPwdDetail");
        // 应用风险信息
        registry.addViewController("/page/appVulnerability/list").setViewName("host/appVulnerability");
        // 弱口令详细信息
        registry.addViewController("/page/appVulnerability/detail").setViewName("host/appVulnerabilityDetail");

        // 日志探测相关信息
        // 全部日志信息
        registry.addViewController("/page/log/list").setViewName("log/list");
        // 日志详情
        registry.addViewController("/page/log/detail").setViewName("log/detail");
        // 登录日志信息
        registry.addViewController("/page/log/loginList").setViewName("log/loginList");
        // 账号日志信息
        registry.addViewController("/page/log/accountList").setViewName("log/accountList");

        // 基线检测相关页面
        // 基线任务列表
        registry.addViewController("/page/baseLineTask/list").setViewName("baseLine/baseLineTask");
        // 基线任务详情
        registry.addViewController("/page/baseLineTask/detail").setViewName("baseLine/baseLineTaskDetail");
        // 基线任务详情编辑
        registry.addViewController("/page/baseLineTask/edit").setViewName("baseLine/baseLineTaskEdit");
        // 基线任务详情添加
        registry.addViewController("/page/baseLineTask/add").setViewName("baseLine/baseLineTaskAdd");
        // 基线检测结果
        registry.addViewController("/page/baseLineResult/list").setViewName("baseLine/baseLineResult");
        // 基线检测结果详情
        registry.addViewController("/page/baseLineResult/detail").setViewName("baseLine/baseLineResultDetail");

    }
}
