package com.jun.sail.feature.conf;

import com.jun.sail.feature.conf.prop.DataProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Jun
 * 创建时间： 2020/5/25
 */

@Component
public class RedisConfig {

    @Autowired
    private DataProperties dataProperties;

    @PostConstruct
    public String test() {
        String countryCode = dataProperties.getUser().getCountryCode();
        System.out.println("get from customer properties: " + countryCode);
        return "rt";
    }


}
