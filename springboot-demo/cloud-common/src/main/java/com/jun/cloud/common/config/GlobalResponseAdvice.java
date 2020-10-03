package com.jun.cloud.common.config;

import com.jun.sail.utils.commons.BaseResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 94977
 * @create 2018/11/6
 */
@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //仅处理返回类型为BaseResponse的方法
        Method method = returnType.getMethod();
        if (method != null) {
            return method.getReturnType().isAssignableFrom(BaseResponse.class);
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof BaseResponse) {
            BaseResponse resp = (BaseResponse) body;
            //resp.setCode(ResponseUtil.formatErrorCode(resp.getCode()));
            //判断是否包含替换符，防止用户已经进行多语言处理或者统一异常已经处理过之后，再次处理导致参数未能替换
            //String msg = ResponseUtil.i18Format(resp.getCode(),resp.getMsg(),null);
            String msg = resp.getMsg();
            if (!StringUtils.isEmpty(msg) && !isMatches(msg)) {
                //resp.setMsg(msg);
                resp.setMsg(msg);
            }
            return resp;
        }
        return body;
    }

    private static final String DEFAULT_QUERY_REGEX = "[{}]";

    private boolean isMatches(String msg) {
        Pattern pattern = Pattern.compile(DEFAULT_QUERY_REGEX);
        Matcher matcher = pattern.matcher(msg);
        return matcher.find();
    }
}
