package com.tpp.threat_perception_platform.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetsParam {
    private String hostname;
    private String mac;
    //账号探测的标记 1 需要探测 0 不需要探测
    private Integer account = 0;
    private Integer service = 0;
    private Integer application = 0;
    private Integer process = 0;

    private String type = "assets";
}
