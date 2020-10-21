package com.jun.sail.appstart.bean.lifecycle;

import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 继承BeanFactoryPostProcessor，允许更多的bean definitions注册，运行在BeanFactoryPostProcessor之前
 * 特别的，这个类可以注册更多的bean definitions，甚至包括BeanFactoryPostProcessor的实例
 */
@Slf4j
@Component
public class CustomerBeanDefinitionPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * 因为BeanDefinitionRegistryPostProcessor继承了BeanFactoryPostProcessor，所以要实现这个接口方法
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    /**
     * 在容器內置的bean definition registry初始化后进行修改。
     * 此时所有普通的bean definitions已经加载，但还没有bean进行实例化。
     * 这个方法允许增加更多的bean definitions，调用下一个post-processing之前
     *
     * @param registry the bean definition registry used by the application context
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.info(AppStartConstant.LOG_SEPARATOR_BEAN + "[ BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry ]");
    }
}
