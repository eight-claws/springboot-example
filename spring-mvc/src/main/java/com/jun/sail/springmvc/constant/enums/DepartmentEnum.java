package com.jun.sail.springmvc.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    /**
     * 此处负责requestBody字段的反序列化，从value得到enum
     * <p>
     * CodeEnum的@JsonValue接口负责的是序列化，和这个方法互相对应
     *
     * One common use case is to use a delegating Creator to construct instances from
     * scalar values (like <code>java.lang.String</code>) during deserialization,
     * and serialize values using {@link JsonValue}
     *
     * @param value 前端传入的字段，据此得到enum
     * @return 对应的enum
     */
    @JsonCreator
    public static DepartmentEnum create(String value) {
        try {
            // 先根据字面量反序列化枚举，实际也可以去掉这个，直接根据下面的去匹配
            return DepartmentEnum.valueOf(value);
        } catch (IllegalArgumentException e) {
            for (DepartmentEnum departmentEnum : DepartmentEnum.values()) {
                if (departmentEnum.getValue().equals(value)) {
                    return departmentEnum;
                }
            }
        }
        throw new IllegalArgumentException("No element matches " + value);
    }
}
