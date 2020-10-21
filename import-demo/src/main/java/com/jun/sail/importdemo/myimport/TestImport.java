package com.jun.sail.importdemo.myimport;

import com.jun.sail.importdemo.myimport.beandefinition.IUserService;
import com.jun.sail.importdemo.myimport.config.EnableCustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@EnableCustomConfig // 注意要声明这个注解，才会生效注解上面的@Import
@Component
public class TestImport {

    @Autowired
    private SpringUtil springUtil;

    @Autowired
    private NormalBean normalBean;

    @Autowired
    private IUserService userService;

    @PostConstruct
    public void init() {
        System.out.println(springUtil.getName());
        System.out.println(normalBean.getName());
        userService.testAdd();
        userService.testGet();
    }
}