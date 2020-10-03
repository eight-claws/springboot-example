package com.jun.cloud.common.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class FilterConfig {

    @Bean
    @Order(2)
    //spring boot 会按照order值的大小，从小到大的顺序来依次过滤
    public FilterRegistrationBean configFilter() {
        FilterRegistrationBean<FilterDemo> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new FilterDemo());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("sessionFilter");
        return filterRegistrationBean;
    }

}
