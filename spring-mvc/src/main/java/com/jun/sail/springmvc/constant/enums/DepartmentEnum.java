package com.jun.sail.springmvc.constant.enums;

import com.jun.sail.springmvc.web.enums.CodeEnum;

/**
 * @Author wangjun
 * @Date 2020/11/4
 **/
public enum DepartmentEnum implements CodeEnum {

    SELL("seller_dep"),
    TEACHER("teacher_dep");

    private String value;

    DepartmentEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String matchCode() {
        return value;
    }
}
