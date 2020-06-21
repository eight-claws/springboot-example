package com.jun.cloud.common.returnvalue;

import com.jun.sail.utils.commons.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * springMVC的接口，处理controller返回值，这样controller的方法返回值就不用写统一的BaseResponse了
 * 注意：当前handler要配置一下才能生效
 */
public class SailResponseReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final Logger logger = LoggerFactory.getLogger(SailResponseReturnValueHandler.class);

    private RequestResponseBodyMethodProcessor target;

    public SailResponseReturnValueHandler(RequestResponseBodyMethodProcessor target) {
        this.target = target;
    }

    /**
     * 表示哪些方法才被当前handler处理
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getContainingClass().isAnnotationPresent(RestController.class)
            && returnType.hasMethodAnnotation(SailResponse.class);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        logger.debug("here comes: {}/{}", returnType, returnValue);

        String desc = null;
        SailResponse annotation = returnType.getMethodAnnotation(SailResponse.class);
        if (annotation != null && !StringUtils.isEmpty(annotation.desc().trim())) {
            desc = annotation.desc();
        }

        //这里只处理成功的请求，如果抛出异常会被统一异常拦截处理
        BaseResponse msg = new BaseResponse(desc, returnValue);

        target.handleReturnValue(msg, returnType, mavContainer, webRequest);
    }

}