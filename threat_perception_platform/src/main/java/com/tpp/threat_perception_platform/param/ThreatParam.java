package com.tpp.threat_perception_platform.param;

import com.tpp.threat_perception_platform.pojo.Account;
import com.tpp.threat_perception_platform.pojo.AppVulnerability;
import com.tpp.threat_perception_platform.pojo.Vulnerability;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreatParam {
    private String hostname;
    private String mac;
    //威胁探测的标记 1 需要探测 0 不需要探测
    private Integer hotfix = 0;
    private Integer vulnerability = 0;
    private Integer application = 0;
    private Integer weakPassword = 0;
    private Integer system = 0;
    //漏洞库
    private List<Vulnerability>  vulnerabilities;

    // 账户列表，传送到客户端进行测试
    private List<Account> accounts;

    // 应用漏洞列表，传送到客户端进行测试
    private List<AppVulnerability> appVulnerabilities;

    private String type = "threat";
}
