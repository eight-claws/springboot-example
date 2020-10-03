package com.jun.sail.propertyread.config;

import com.jun.sail.propertyread.props.DataProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jun
 * 创建时间： 2020/5/25
 */

@Configuration
public class RedisConfig {

    @Autowired
    private DataProperties dataProperties;

    @Bean
    public String tet() {
        dataProperties.getUser().getCountryCode();
        return "rt";
    }


}
