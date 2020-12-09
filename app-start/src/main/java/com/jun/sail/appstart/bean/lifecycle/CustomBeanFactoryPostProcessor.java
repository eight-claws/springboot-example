package com.jun.sail.appstart.bean.lifecycle;

import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

    /**
     * 用来在所有的bean definitions加载，但是还没有实例化bean之前调用本方法，所以本方法非常超前执行
     * 可以修改bean的定义，如是否是单例，是否lazy init，DependsOn，FactoryBeanName等等等等，一般用来修改属性值，
     * 一个典型的实现是PropertyResourceConfigurer，用来从配置文件里加载属性放进bean里，或者更换${...}placeHolder
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info(AppStartConstant.LOG_SEPARATOR_BEAN + "[ BeanFactoryPostProcessor.postProcessBeanFactory ]");

        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("myBean");

        // 这里通过构造器的参数来指定之后实例化myBean时使用哪个构造器，即通过value的类型来选用
        ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
        constructorArgumentValues.addIndexedArgumentValue(0, "ali");
    }

    @Override
    public int getOrder() {
        return 11;
    }
}
