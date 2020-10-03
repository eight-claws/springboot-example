package com.jun.cloud.service.common.enums;

/**
 * 用于记录需要分表的表名和字段
 *
 * @author Jun
 * 创建时间： 2019/7/7
 */
public enum SubTableEnum {

    TB_ORIGINAL_DATA("original_data", "device_time"),
    TB_REGION("region", "create_time");


    private String tableName;

    private String subCol;


    SubTableEnum(String tableName, String subCol) {
        this.tableName = tableName;
        this.subCol = subCol;
    }

    public String getTableName() {
        return tableName;
    }

    public String getSubCol() {
        return subCol;
    }

}
