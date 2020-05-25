package com.jun.sail.appstart.config;

import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * @author Jun
 * 创建时间： 2020/5/17
 */
@Component
@Slf4j
public class AppTestInitializer implements SmartInitializingSingleton {


    @Override
    public void afterSingletonsInstantiated() {
        log.info(AppStartConstant.LOG_SPARATOR + "app init from SmartInitializingSingleton-afterSingletonsInstantiated()");
    }
}
