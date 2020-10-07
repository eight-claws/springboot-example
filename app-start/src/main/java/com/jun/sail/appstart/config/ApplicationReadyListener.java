package com.jun.sail.appstart.config;

import com.jun.sail.HelloService;
import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * ApplicationRunner和CommandLineRunner是容器启动过程中的回调
 *
 * 注意下面三种方式，发生异常都会导致容器启动失败
 *
 * @author Jun
 * 创建时间： 2020/5/16
 */
@Slf4j
@Component
public class ApplicationReadyListener implements ApplicationRunner, CommandLineRunner {

    @Autowired
    private HelloService helloService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(AppStartConstant.LOG_SPARATOR + "[ ApplicationRunner ]");

        System.out.println(helloService.sayHello("jun"));

        // 不捕获会导致容器启动失败
        try {
            // throw new RuntimeException("任务执行失败");
        } catch (Exception e) {
            log.error("fail ,app start error", e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(AppStartConstant.LOG_SPARATOR + "[ CommandLineRunner ]");
    }

    @EventListener(classes = ApplicationReadyEvent.class)
    public void processInit() {
        log.info(AppStartConstant.LOG_SPARATOR + "[ listener ApplicationReadyEvent ]");
        // throw new RuntimeException("任务执行失败");
    }

}
