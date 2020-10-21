package com.jun.sail.appstart.bean.lifecycle;

import com.jun.sail.appstart.constants.AppStartConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerBeanPostProcessor implements BeanPostProcessor {


    /**
     * 在bean进行初始化方法的回调（如InitializingBean.afterPropertiesSet或者自定义的init方法@PostConstruct）之前调用
     * 调用此方法时，bean的属性值已经设置好
     * 可以返回一个包装类
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyBean) {
            MyBean myBean = (MyBean) bean;
            log.info(AppStartConstant.LOG_SEPARATOR_BEAN + "[ BeanPostProcessor.postProcessBeforeInitialization ]");
        }
        return bean;
    }

    /**
     * 在bean进行初始化方法的回调（如InitializingBean.afterPropertiesSet或者自定义的init方法@PostConstruct）之后调用
     * 调用此方法时，bean的属性值已经设置好
     * 可以返回一个包装类
     * 可能被多次调用
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyBean) {
            MyBean myBean = (MyBean) bean;
            log.info(AppStartConstant.LOG_SEPARATOR_BEAN + "[ BeanPostProcessor.postProcessAfterInitialization ]");
        }
        return bean;
    }


}
