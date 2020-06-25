package com.jun.sail.appstart.config;

import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author Jun
 * 创建时间： 2020/5/16
 */
@Slf4j
@Component
public class EventListenerDemo {

    @Getter
    private HashMap<String, String> typeMap;

    @EventListener(classes = ApplicationReadyEvent.class)
    public void processInit(){

        log.info(AppStartConstant.LOG_SPARATOR + "[ listener ApplicationReadyEvent ]");
    }


    /**
     * 用来在属性注入后执行一些初始化方法，没有返回值和参数
     *
     * 可以用来初始化一些OSSClient连接对象，或者初始化常量map等
     */
    @PostConstruct
    public void init(){
        log.info(AppStartConstant.LOG_SPARATOR + "[ PostConstruct ]");

        typeMap = new HashMap<>();
        typeMap.put("1", "IOS");
        typeMap.put("2", "ANDROID");
        typeMap.put("3", "ANDROID-GOOGLEPLAY");
    }


}
