package com.jun.sail.appstart.myimport;

import com.jun.sail.appstart.myimport.beandefinition.AsyncConfigImportRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({SpringUtil.class, BoyImportSelector.class, AsyncConfigImportRegistrar.class})
public @interface EnableCustomConfig {
    // 注解的属性，默认为true
    boolean flag() default true;


    // proxyTargetClass=true直接对类进行代理，即使用cglib；proxyTargetClass=false则使用jdk代理
    boolean proxyTargetClass() default false;

    // 指定异步执行线程池
    String executor() default "defaultExecutor";
}
