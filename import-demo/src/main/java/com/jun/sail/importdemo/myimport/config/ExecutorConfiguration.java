package com.jun.sail.importdemo.myimport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class ExecutorConfiguration {

    @Bean
    public ExecutorService defaultExecutor() {
        final ThreadFactory threadFactory = Thread::new;
        return Executors.newFixedThreadPool(5, threadFactory);
    }
}