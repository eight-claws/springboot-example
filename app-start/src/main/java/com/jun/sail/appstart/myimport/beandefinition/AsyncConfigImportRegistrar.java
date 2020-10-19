package com.jun.sail.appstart.myimport.beandefinition;

import com.jun.sail.appstart.myimport.EnableCustomConfig;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.util.Assert;

public class AsyncConfigImportRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // importingClassMetadata封装了配置类元信息，通过getAnnotationAttributes()可以获取到配置类上指定注解属性Map
        AnnotationAttributes attributes = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(EnableCustomConfig.class.getName()));
        Assert.notNull(attributes, "@MyEnableAsync attributes is null");

        RootBeanDefinition beanDefinition = new RootBeanDefinition(AsyncAnnotationBeanPostProcessor.class);
        MutablePropertyValues pvs = beanDefinition.getPropertyValues();
        pvs.addPropertyValue("proxyTargetClass", attributes.getBoolean("proxyTargetClass"));
        pvs.addPropertyValue("executor", new RuntimeBeanReference(attributes.getString("executor")));
        registry.registerBeanDefinition("asyncAnnotationBeanPostProcessor", beanDefinition);
    }

}