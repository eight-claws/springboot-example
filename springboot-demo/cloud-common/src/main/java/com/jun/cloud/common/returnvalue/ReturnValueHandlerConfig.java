package com.jun.cloud.common.returnvalue;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置SailResponseReturnValueHandler
 *
 * 其实@Responsebody注解也是基于ReturnValueHandler，相当于把返回值用jackson转成json了
 */
@Configuration
public class ReturnValueHandlerConfig implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        if (returnValueHandlers == null) {
            returnValueHandlers = new ArrayList<>();
        }
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(returnValueHandlers);

        this.decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }

    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.stream()
            .filter(handler -> handler instanceof RequestResponseBodyMethodProcessor)
            .findFirst()
            .ifPresent(handler -> {
                handlers.add(handlers.indexOf(handler), new SailResponseReturnValueHandler((RequestResponseBodyMethodProcessor) handler));
                //handlers.add(handlers.indexOf(handler), new PagedResponseReturnValueHandler((RequestResponseBodyMethodProcessor) handler));
            });
    }

}