package com.jun.cloud.common.exception;

import com.jun.cloud.common.common.errorcode.DefaultErrorCode;
import com.jun.sail.sailI18n.I18nUtils;
import com.jun.sail.utils.check.ObjectUtils;
import com.jun.sail.utils.commons.BaseResponse;
import com.jun.sail.utils.commons.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 统一异常处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * post请求校验失败
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder errorInfo = new StringBuilder();
        BindingResult bindingResult = exception.getBindingResult();
        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            if (i > 0) {
                errorInfo.append(" ; ");
            }
            FieldError fieldError = bindingResult.getFieldErrors().get(i);

            String message = I18nUtils.getMessage(fieldError.getDefaultMessage());
            errorInfo.append("参数").append(fieldError.getField()).append("[")
                    .append(fieldError.getRejectedValue()).append("]")
                    .append(": ").append(message);
        }

        //返回BaseResponse
        BaseResponse<String> response = new BaseResponse<>();
        response.setMsg(errorInfo.toString());
        response.setCode(DefaultErrorCode.error);
        return response;
    }

    /**
     * get请求的参数校验失败
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> handleConstraintViolationException(ConstraintViolationException exception) {
        StringBuilder errorInfo = new StringBuilder();
        String errorMessage;

        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for (ConstraintViolation<?> item : violations) {
            errorInfo.append(item.getMessage()).append(" ; ");
        }
        errorMessage = errorInfo.toString().substring(0, errorInfo.toString().length() - 1);

        BaseResponse<String> response = new BaseResponse<>();
        response.setMsg(errorMessage);
        response.setCode(DefaultErrorCode.error);
        return response;
    }

    /**
     * 处理一些没有传值的情况
     */
    @ExceptionHandler
    public BaseResponse handleBindException(BindException exception) {
        List<ObjectError> errors = exception.getAllErrors();
        String msg = null;
        if (ObjectUtils.isNotEmpty(errors)) {
            int first = 0;
            for (int i = 0; i < errors.size(); i++) {
                ObjectError error = errors.get(i);
                String message = error.getDefaultMessage();
                String field = null;
                if (error instanceof FieldError) {
                    field = ((FieldError) error).getField();
                }

                String i18Msg = I18nUtils.getMessage(message);
                if (ObjectUtils.isEmpty(i18Msg)) {
                    i18Msg = msg;
                }
                if (i == first) {
                    //取一个错误信息传给前端
                    msg = i18Msg;
                }
                log.error("validate error,{}, {}", field, message);
            }
        }

        BaseResponse<String> response = new BaseResponse<>();
        response.setMsg(msg);
        response.setCode(msg);
        return response;
    }


    /**
     * 处理自定义异常
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> handleBaseRuntimeException(BaseRuntimeException exception) {
        BaseResponse<String> response = new BaseResponse<>();
        response.setMsg(exception.getMessage());
        response.setCode(DefaultErrorCode.error);
        return response;
    }


    /**
     * 处理其他未知异常
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> handleDefaultException(Exception exception) {
        BaseResponse<String> response = new BaseResponse<>();
        response.setMsg("未知错误");
        response.setCode(DefaultErrorCode.error);
        return response;
    }
}

