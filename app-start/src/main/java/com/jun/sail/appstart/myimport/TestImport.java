package com.jun.sail.appstart.myimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@EnableCustomConfig // 注意要声明这个注解，才会生效注解上面的@Import
@Component
public class TestImport {
    @Autowired
    private SpringUtil springUtil;

    @Autowired
    private Boy boy;

    @Autowired
    private Dog dog;

    @PostConstruct
    public void init() {
        System.out.println(springUtil.getName());
        System.out.println(boy.getName());
        System.out.println(dog.getName());
    }
}