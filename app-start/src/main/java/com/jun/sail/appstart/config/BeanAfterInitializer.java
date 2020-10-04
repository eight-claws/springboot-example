package com.jun.sail.appstart.config;

import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * 关于SmartInitializingSingleton和InitializingBean的区别：https://blog.csdn.net/lkforce/article/details/106721239
 * @author Jun
 * 创建时间： 2020/5/17
 */
@Component
@Slf4j
public class BeanAfterInitializer implements SmartInitializingSingleton, InitializingBean {

    /**
     * 在所有的单例bean都加载完成后，再一次从beanDefinitionNames列表中获得所有要注入的bean，
     * 调用他们的这个方法
     */
    @Override
    public void afterSingletonsInstantiated() {
        log.info(AppStartConstant.LOG_SPARATOR + "[ SmartInitializingSingleton ]");
    }

    /**
     * 在getBean()方法注入bean时，调用这个方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(AppStartConstant.LOG_SPARATOR + "[ InitializingBean ]");
    }

    @Getter
    private HashMap<String, String> typeMap;

    /**
     * 用来在属性注入后执行一些初始化方法，没有返回值和参数
     * <p>
     * 可以用来初始化一些OSSClient连接对象，或者初始化常量map等
     */
    @PostConstruct
    public void init() {
        log.info(AppStartConstant.LOG_SPARATOR + "[ PostConstruct ]");

        typeMap = new HashMap<>();
        typeMap.put("1", "IOS");
        typeMap.put("2", "ANDROID");
        typeMap.put("3", "ANDROID-GOOGLEPLAY");
    }


}
