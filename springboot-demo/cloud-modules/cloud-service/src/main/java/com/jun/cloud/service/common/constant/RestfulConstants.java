package com.jun.cloud.service.common.constant;

/**
 * 常量类
 * @author Jun
 * 创建时间： 2019/5/12
 */
public class RestfulConstants {

    /**
     * 名称的最大长度20个
     */
    public static final int NAME_MAX_LENGTH = 20;

    /**
     * userAddDto里最多包含12个direction
     */
    public static final int DIRECTION_MAX_NUMBER = 12;


    /**
     * 数据表中delete_status字段 0-未删除，1-已删除
     */
    public static final Integer DELETE_STATUS_NOT_DELETE = 0;
    public static final Integer DELETE_STATUS_IS_DELETE = 1;


    /**
     * 自定义enum check中用于校验的方法名
     */
    public static final String ENUM_CHECK_METHOD_NAME = "validation";


}
