package com.jun.sail.appstart.config;

import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Jun
 * 创建时间： 2020/5/16
 */
@Slf4j
@Component
public class EventListenerDemo {

    @EventListener(classes = ApplicationReadyEvent.class)
    public void init(){

        log.info(AppStartConstant.LOG_SPARATOR + "[ listener ApplicationReadyEvent ]");
    }


}
