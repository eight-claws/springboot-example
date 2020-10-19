package com.jun.sail.appstart.myimport;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Objects;

public class DogBeanDefinition implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attrs = AnnotatedElementUtils.getMergedAnnotationAttributes(
                ClassUtils.resolveClassName(importingClassMetadata.getClassName(), null), EnableCustomConfig.class);
        // 通过EnableCustomConfig注解中属性值来控制是否加载bean
        if (Objects.nonNull(attrs) && Boolean.TRUE.equals(attrs.getBoolean("flag"))) {
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Dog.class);
            registry.registerBeanDefinition("Dog22", beanDefinition);
        }
    }
}