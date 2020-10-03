package com.jun.sail.appstart.config;

import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Jun
 * 创建时间： 2020/5/16
 */
@Slf4j
@Component
public class ApplicationRunnerDemo implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {

            log.info(AppStartConstant.LOG_SPARATOR + "[ ApplicationRunner ]");
            //throw new RuntimeException("任务执行失败");
        } catch (Exception e) {
            log.error("fail ,app start error", e);
        }

    }
}
