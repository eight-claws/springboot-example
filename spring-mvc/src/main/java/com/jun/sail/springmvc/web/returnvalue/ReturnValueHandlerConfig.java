package com.jun.sail.springmvc.web.returnvalue;

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
 * <p>
 *
 */
// @Configuration
public class ReturnValueHandlerConfig implements InitializingBean {

    /**
     * 这个adapter非常关键，它初始化了所有的参数解析器和返回值处理器
     */
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

    /**
     * 这里装饰下@Responsebody的RequestResponseBodyMethodProcessor，
     * 也就是在把返回值交给他之前先调用SailResponseReturnValueHandler，把返回值包装成RestResult
     * 然后再由RequestResponseBodyMethodProcessor把返回值处理成json
     */
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