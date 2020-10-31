package com.jun.sail.springmvc.config;


import com.jun.sail.springmvc.web.returnvalue.SailResponseReturnValueHandler;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;


/**
 * WebMvc配置：
 * 1.消息转换器的配置，提供Jackson的支持
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {




}