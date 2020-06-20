package com.jun.cloud.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description 事件处理线程池配置，避免事件处理耗时而影响默认线程池
 * @Since 1.0
 */
@Configuration
@EnableAsync//开启异步任务的支持
public class EventProcessThreadPoolConfig {

    /**
     * 阻塞任务队列大小
     */
    private static final Integer QUEUE_CAPACITY_NUM = 100;

    /**
     * 线程名称前缀
     */
    private static final String THREAD_NAME_PREFIX = "cloud-eventProcess-";

    /**
     * 线程池中任务的等待时间
     */
    private static final Integer AWAIT_TERMINATION_SECONDS = 60;

    @Bean(value = "businessEventProcessTaskExecutor", destroyMethod = "destroy")
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 获取JAVA虚拟机的可用处理器数量。IO密集型建议核心线程数是该值2倍；计算密集型建议核心线程数是该值1倍
        int processorNum = Runtime.getRuntime().availableProcessors();

        // 设置核心线程数量。若池中的实际线程数小于该值，无论其中是否有空闲的线程，都会产生新的线程
        executor.setCorePoolSize(processorNum * 2);

        // 设置最大线程数量
        executor.setMaxPoolSize(processorNum * 4);

        // 设置阻塞任务队列大小
        executor.setQueueCapacity(QUEUE_CAPACITY_NUM);

        // 线程名称前缀
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);

        // 设置线程池关闭时等待所有任务都完成再继续销毁其他Bean，这样异步任务的销毁就会先于Redis线程池的销毁
        executor.setWaitForTasksToCompleteOnShutdown(true);

        // 设置线程池中任务的等待时间，若超过等待时间仍未销毁则强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(AWAIT_TERMINATION_SECONDS);

        // 设置拒绝策略，当线程池阻塞队列已满时对新任务的处理。调节机制，即饱和时回退主线程执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}