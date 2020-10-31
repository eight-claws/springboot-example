package com.jun.sail.springmvc.web.returnvalue;

import com.jun.sail.springmvc.web.result.RestResult;
import com.jun.sail.springmvc.web.result.RestResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;

/**
 * springMVC的接口，处理controller返回值，这样controller的方法返回值就不用写统一的BaseResponse了
 * 注意：当前handler要配置一下才能生效
 */
public class SailResponseReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final Logger logger = LoggerFactory.getLogger(SailResponseReturnValueHandler.class);

    /**
     * 这里拿到处理@ResponseBody的处理器
     */
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
        ServletServerHttpRequest serverHttpRequest = createInputMessage(webRequest);
        logger.info("here comes: {}/{}, from {}", returnType, returnValue, serverHttpRequest.getURI());

        String desc = null;
        SailResponse annotation = returnType.getMethodAnnotation(SailResponse.class);
        if (annotation != null && !StringUtils.isEmpty(annotation.desc().trim())) {
            desc = annotation.desc();
        }

        // 这里只处理成功的请求，如果抛出异常会被统一异常拦截处理
        RestResult<Object> result = RestResultUtils.success(returnValue, desc);

        target.handleReturnValue(result, returnType, mavContainer, webRequest);
    }

    private ServletServerHttpRequest createInputMessage(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.state(servletRequest != null, "No HttpServletRequest");
        return new ServletServerHttpRequest(servletRequest);
    }

}