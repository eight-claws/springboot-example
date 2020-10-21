package com.jun.sail.apollo.config.prop;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 通过JavaBean的方式注入，统一管理，感觉可以一个namespace对应一个bean，分类管理
 * 注意需要声明这个Bean
 */
@Data
@Component
public class TestJavaConfigBean {

    @Value("${timeout:100}")
    private int timeout;

    private int batch;

    @Value("${batch:200}")
    public void setBatch(int batch) {
        this.batch = batch;
    }

}