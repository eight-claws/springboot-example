package com.jun.sail.springbootsecurit.service;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.Assert;

/**
 * @author Jun
 * 创建时间： 2020/6/21
 */
public class BCryptTest {

    @Test
    public void testBCrypt(){
        String password = "123456";

        //BCrypt每次生成的密文都不一致
        String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        String hashpw2 = BCrypt.hashpw(password, BCrypt.gensalt());

        //这里两次生成的密文都不一样，但都和123456比对成功
        Assert.state(BCrypt.checkpw(password, hashpw), "密码错误");
        Assert.state(BCrypt.checkpw(password, hashpw2), "密码错误");

        System.out.println(hashpw);
        System.out.println(hashpw2);
    }


}
