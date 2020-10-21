package com.jun.sail.importdemo.myimport.beandefinition;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyConfig;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class MyAsyncAnnotationBeanPostProcessor extends ProxyConfig implements BeanPostProcessor {

    private ExecutorService executor;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        // 查看当前bean 是否有@MyAsync注解的方法
        MethodIntrospector.MetadataLookup<MyAsync> lookup = method -> AnnotatedElementUtils.findMergedAnnotation(method, MyAsync.class);
        Map<Method, MyAsync> methods = MethodIntrospector.selectMethods(bean.getClass(), lookup);

        // 如果Bean含有@MyAsync注解方法，则对Bean进行代理，并对@MyAsync注解方法进行增强
        if (!methods.isEmpty()) {
            //定义一个注解切点，只对@MyAsync注解方法进行切入
            AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(MyAsync.class);
            //定义一个Advice，封装增强逻辑代码
            Advice advice = new AsyncAnnotationAdvice(executor);

            //使用一个Advisor将增强逻辑Advice和切点PointCut封装到一起
            DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
            advisor.setPointcut(pointcut);
            advisor.setAdvice(advice);

            //创建一个Spring代理工具类
            ProxyFactory proxyFactory = new ProxyFactory();
            //指定代理目标实例
            proxyFactory.setTarget(bean);
            if (!this.isProxyTargetClass()) {
                //使用jdk代理方式
                proxyFactory.setInterfaces(bean.getClass().getInterfaces());
            }

            //advisor包含了织入点和织入逻辑，ProxyFactory就会根据这些创建出代理对象
            proxyFactory.addAdvisor(advisor);
            //使用当前类的属性
            proxyFactory.copyFrom(this);
            return proxyFactory.getProxy();
        }
        return bean;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }
}