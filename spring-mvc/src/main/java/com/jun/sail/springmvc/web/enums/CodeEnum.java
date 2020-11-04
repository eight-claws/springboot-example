package com.jun.sail.springmvc.web.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 作为spring-mvc参数里的枚举字段需要继承这个接口，
 *
 * @Author wangjun
 * @Date 2020/11/4
 **/
public interface CodeEnum {

    /**
     * 这个注解用于对应@RequestBody里枚举的转换（反序列化），和PathVariable，RequestParam的枚举转换无关
     * 注解@JsonValue 注解的方法，用来表示json序列化时的值。用在注解中时，也可以根据方法的返回值进行反序列化
     */
    @JsonValue
    String matchCode();

}
