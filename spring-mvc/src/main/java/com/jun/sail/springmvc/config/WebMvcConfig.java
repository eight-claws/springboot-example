package com.jun.sail.springmvc.config;


import com.jun.sail.springmvc.web.enums.CodedEnumConverterFactory;
import org.springframework.context.annotation.Configuration;

import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * WebMvc配置：
 * 1.消息转换器的配置，提供Jackson的支持
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new CodedEnumConverterFactory());
    }


}