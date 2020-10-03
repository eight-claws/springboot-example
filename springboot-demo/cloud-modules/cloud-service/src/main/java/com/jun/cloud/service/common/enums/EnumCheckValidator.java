package com.jun.cloud.service.common.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

public class EnumCheckValidator implements ConstraintValidator<EnumCheck, Object> {

    private static final Logger logger = LoggerFactory.getLogger(EnumCheckValidator.class);

    private EnumCheck enumCheck;

    @Override
    public void initialize(EnumCheck enumCheck) {
        this.enumCheck = enumCheck;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 注解表明为必选项 则不允许为空，否则可以为空
        if (value == null) {
            return !this.enumCheck.required();
        }

        Boolean result = Boolean.FALSE;
        // 获取参数的数据类型
        Class<?> valueClass = value.getClass();
        try {
            Method method = this.enumCheck.enumClass().getMethod(this.enumCheck.enumMethod(), valueClass);
            result = (Boolean) method.invoke(null, value);
            if (result == null) {
                return false;
            }
        } catch (Exception e) {
            logger.error("custom EnumCheckValidator error", e);
        }
        return result;
    }
}