package com.jun.cloud.common.apprunner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 这里演示ApplicationRunner和CommandLineRunner的用法
 * 具体查看博客https://blog.csdn.net/java_collect/article/details/87300805
 */
@Component
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {

    /**
     * 这里可以做些定时任务的初始化、检测其他组件是否启动等··
     * 注意要try catch，即此处抛出异常会影响项目启动
     */
    @Override
    public void run(ApplicationArguments args) {
        try {
            //System.out.println(args);
            //System.out.println("-----演示CommandLineRunner和ApplicationRunner");
        } catch (Exception e) {
            log.error("app runner task error!");
        }
    }
}