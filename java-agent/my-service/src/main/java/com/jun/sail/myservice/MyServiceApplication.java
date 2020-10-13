package com.jun.sail.myservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;

@SpringBootApplication
public class MyServiceApplication {

    public static void main(String[] args) {
        // 获取当前虚拟机的pid
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

        SpringApplication.run(MyServiceApplication.class, args);
    }

}
