package com.jun.sail.feature.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @Author wangjun
 * @Date 2021/1/5
 **/
@Service
public class ResourceHandler {
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void testGetResource(){
        // ApplicationContext实现了ResourceLoader接口,所以下面可以getResource()

        // 从本地File System获取
        Resource resource = applicationContext.getResource("file:///E:/code/github/doc-for-spring/pages/spring/Overview.md");

        // 从classpath获取
         Resource classpathResource = applicationContext.getResource("classpath:/application.properties");

        // 从网络获取http
        Resource httpResource = applicationContext.getResource("https://docs.spring.io/spring-framework/docs/current/reference/html/images/container-magic.png");
        try {
            System.out.println(resource.contentLength());
        } catch (IOException e) {

        }
    }

}
