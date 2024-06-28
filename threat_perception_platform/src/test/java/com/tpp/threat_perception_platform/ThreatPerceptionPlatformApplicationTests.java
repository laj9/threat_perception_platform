package com.tpp.threat_perception_platform;

import com.tpp.threat_perception_platform.pojo.User;
import com.tpp.threat_perception_platform.utils.Md5Util;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(classes = ThreatPerceptionPlatformApplication.class)
class ThreatPerceptionPlatformApplicationTests {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void test01() {
        // $2a$10$l4/yfZ1SuK/8tpafODPLnuryGoL6YpZC4aYOhApSQ0Sqq4LSGrkBq
        System.out.println(bCryptPasswordEncoder.encode("123123"));

    }

}
