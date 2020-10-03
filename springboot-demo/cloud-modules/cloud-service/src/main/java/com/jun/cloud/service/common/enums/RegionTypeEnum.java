package com.jun.cloud.service.common.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 工地类型枚举
 */
public enum RegionTypeEnum implements IEnum<Integer> {

    REGION_NORMAL(1, "普通"),
    REGION_SITE(2, "工地");


    @EnumValue
    private final int type;

    private final String desc;


    RegionTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

    @Override
    public Integer getValue() {
        return type;

    }
}