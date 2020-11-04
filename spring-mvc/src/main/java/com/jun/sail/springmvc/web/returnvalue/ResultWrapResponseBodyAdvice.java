package com.jun.sail.springmvc.web.returnvalue;

import com.jun.sail.springmvc.web.result.RestResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 这里是SailResponse的另外一种实现方式。利用ResponseBodyAdvice，在@ResponseBody执行后但是还没有被HttpMessageConverter写回
 *
 * 通过RequestMappingHandlerAdapter注册，ExceptionHandlerExceptionResolver注册，或者直接标注@ControllerAdvice都可以生效
 * 多个ResponseBodyAdvice可以通过order排序
 *
 * @Author wangjun
 * @Date 2020/10/31
 **/
@ControllerAdvice
@Slf4j
@Order(2)
public class ResultWrapResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getContainingClass().isAnnotationPresent(RestController.class)
                && returnType.hasMethodAnnotation(SailResponse.class);
    }

    /**
     * RequestResponseBodyMethodProcessor的handleReturnValue方法执行时会回调这个方法
     * 在HttpMessageConverter的write方法之前调用
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("here comes: {}/{}, from {}", returnType, body, request.getURI());
        // 这里只处理成功的请求，如果抛出异常会被统一异常拦截处理
        String desc = "";
        SailResponse annotation = returnType.getMethodAnnotation(SailResponse.class);
        if (annotation != null && !StringUtils.isEmpty(annotation.desc().trim())) {
            desc = annotation.desc();
        }

        return RestResultUtils.success(body, desc);
    }
}
