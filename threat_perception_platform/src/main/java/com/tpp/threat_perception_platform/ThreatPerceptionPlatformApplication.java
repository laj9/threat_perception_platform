package com.tpp.threat_perception_platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.tpp.threat_perception_platform.dao")
@EnableScheduling
public class ThreatPerceptionPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreatPerceptionPlatformApplication.class, args);
    }

}
