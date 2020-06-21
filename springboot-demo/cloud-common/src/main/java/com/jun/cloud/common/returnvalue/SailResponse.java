package com.jun.cloud.common.returnvalue;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Use this annotation to wrap response json format
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SailResponse {

    @AliasFor("desc")
    String value() default "";

    @AliasFor("value")
    String desc() default "";

}