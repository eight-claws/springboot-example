package com.jun.cloud.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Jun
 * 创建时间： 2019/6/9
 */
@Configuration
@MapperScan("com.jun.cloud.**.mapper")
public class MybatisPlusConfig {

    /**
     * service-plus SQL执行效率插件（只在开发环境下使用）
     */
    @Bean
    @Profile("dev")
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }


}
