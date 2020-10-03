package com.jun.cloud.restful.common.enums;

import java.util.Objects;

public enum SexEnum {

    MAN("男", 1),
    WOMAN("女", 2);

    private String label;
    private Integer value;


    SexEnum(String label, int value) {
        this.label = label;
        this.value = value;
    }

    /**
     * 判断值是否满足枚举中的value
     */
    public static boolean validation(Integer value) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (Objects.equals(sexEnum.getValue(), value)) {
                return true;
            }
        }
        return false;
    }


    public String getLabel() {
        return label;
    }

    public Integer getValue() {
        return value;
    }
}