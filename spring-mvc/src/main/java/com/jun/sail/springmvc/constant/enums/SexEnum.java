package com.jun.sail.springmvc.constant.enums;

import com.jun.sail.springmvc.web.enums.CodeEnum;

/**
 * 表示性别的枚举
 *
 * @Author wangjun
 * @Date 2020/11/4
 **/
public enum SexEnum implements CodeEnum {

    MAN(1),
    WOMAN(2);

    private final int value;

    SexEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String matchCode() {
        return String.valueOf(this.value);
    }
}
