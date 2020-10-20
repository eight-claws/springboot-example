package com.jun.sail.appstart.myimport.beandefinition;

import com.jun.sail.appstart.myimport.config.EnableCustomConfig;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

public class AsyncConfigBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // importingClassMetadata封装了配置类(即添加@import的注解的最终类，本文就是TestImport)元信息，
        // 通过getAnnotationAttributes()可以获取到指定注解属性Map
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableCustomConfig.class.getName()));
        Assert.notNull(attributes, "@MyEnableAsync attributes is null");

        RootBeanDefinition beanDefinition = new RootBeanDefinition(MyAsyncAnnotationBeanPostProcessor.class);
        MutablePropertyValues pvs = beanDefinition.getPropertyValues();
        pvs.addPropertyValue("proxyTargetClass", attributes.getBoolean("proxyTargetClass"));
        // 运行时从容器中获取名为defaultExecutor的线程池
        pvs.addPropertyValue("executor", new RuntimeBeanReference(attributes.getString("executor")));
        registry.registerBeanDefinition("myAsyncAnnotationBeanPostProcessor", beanDefinition);
    }

}