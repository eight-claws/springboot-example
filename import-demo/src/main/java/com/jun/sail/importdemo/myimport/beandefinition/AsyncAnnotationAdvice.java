package com.jun.sail.importdemo.myimport.beandefinition;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AsyncAnnotationAdvice implements MethodInterceptor {
    private ExecutorService executor;

    public AsyncAnnotationAdvice(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        // 对目标方法调用封装到一个Callable实例中
        Callable<Object> task = () -> {
            try {
                Object result = invocation.proceed();
                if (result instanceof Future) {
                    return ((Future<?>) result).get();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return null;
        };

        // 然后放入到线程池中执行
        if (executor != null) {
            executor.submit(task);
        } else {
            invocation.proceed();
        }
        return null;
    }

}