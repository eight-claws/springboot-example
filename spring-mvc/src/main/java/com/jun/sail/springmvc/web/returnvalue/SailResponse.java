package com.jun.sail.springmvc.web.returnvalue;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

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