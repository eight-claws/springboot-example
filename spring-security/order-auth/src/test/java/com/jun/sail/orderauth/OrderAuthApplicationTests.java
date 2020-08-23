package com.jun.sail.orderauth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class OrderAuthApplicationTests {

    @Test
    void passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println("加密后的密码" + encode);
        boolean matches = passwordEncoder.matches("123456", encode);
        System.out.println(matches);
    }

}
