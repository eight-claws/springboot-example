package com.jun.sail.springcore;

import com.jun.sail.springcore.service.UserDto;
import com.jun.sail.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCoreApplication.class, args);
    }

    @Autowired
    private UserService userService;

    @PostConstruct
    public void saveUser() {
        userService.saveUser(new UserDto(12, "小红"));
    }
}
