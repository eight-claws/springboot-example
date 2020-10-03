package com.jun.cloud.service.common.enums;


/**
 * @author Jun
 * 创建时间： 2019/6/9
 */
public enum DeleteStatusEnum {

    DELETE(1, "已删除"),
    NOT_DELETE(0, "未删除");

    private Integer status;
    private String desc;


    DeleteStatusEnum(Integer deleteStatus, String desc) {
        this.status = deleteStatus;
        this.desc = desc;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getDesc() {
        return this.desc;
    }


}
