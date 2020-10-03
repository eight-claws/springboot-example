package com.jun.cloud.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 默认线程池配置：返回bean=taskExecutor
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);

    @Value("${thread.pool.executor.core_pool_size:10}")
    private int corePoolSize;

    @Value("${thread.pool.executor.max_pool_size:20}")
    private int maxPoolSize;

    @Value("${thread.pool.executor.queue_capacity:50}")
    private int queueCapacity;

    /**
     * 线程名前缀
     */
    @Value("${thread.pool.executor.name.prefix:taskExecutor-}")
    private String namePrefix;

    @Override
    @Bean(value = "taskExecutor", destroyMethod = "destroy")
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(corePoolSize);

        //空闲等待时间，默认60s
        pool.setKeepAliveSeconds(60);

        pool.setMaxPoolSize(maxPoolSize);

        //设置缓冲队列大小
        pool.setQueueCapacity(queueCapacity);

        //线程名称前缀
        pool.setThreadNamePrefix(namePrefix);

        //用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        pool.setWaitForTasksToCompleteOnShutdown(true);

        //该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        pool.setAwaitTerminationSeconds(60);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //增加线程池执行的包装方法，输出错误日志（可参考测试代码）
        pool.setTaskDecorator(runnable -> () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                logger.error("- thread error", e);
            }
        });

        pool.initialize();
        return pool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (Throwable throwable, Method method, Object... objects) -> {
            logger.error("- taskExecutor execute error,", throwable);
            logger.error("- uncaught exception,method:{},param:{}", method, objects);
        };
    }

}