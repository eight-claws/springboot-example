package com.jun.sail.appstart.start.listener;

import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * ApplicationRunner和CommandLineRunner是容器启动过程中的回调
 * <p>
 * 注意下面三种方式，发生异常都会导致容器启动失败
 *
 * @author Jun
 * 创建时间： 2020/5/16
 */
@Slf4j
@Component
public class ApplicationReadyListener implements ApplicationRunner, CommandLineRunner {

    @EventListener(classes = ApplicationReadyEvent.class)
    public void processInit() {
        log.info(AppStartConstant.LOG_SEPARATOR_APP + "[ listener ApplicationReadyEvent ]");
        // throw new RuntimeException("任务执行失败");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(AppStartConstant.LOG_SEPARATOR_APP + "[ ApplicationRunner ]");

        // 不捕获会导致容器启动失败
        try {
            // throw new RuntimeException("任务执行失败");
        } catch (Exception e) {
            log.error("fail ,app start error", e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(AppStartConstant.LOG_SEPARATOR_APP + "[ CommandLineRunner ]");
    }

    /**
     * 和继承CommandLineRunner作用类似，其实也就是声明了一个CommandLineRunner的实现类
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }



}
