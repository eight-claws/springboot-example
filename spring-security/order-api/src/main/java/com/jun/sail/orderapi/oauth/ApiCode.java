package com.jun.sail.orderapi.oauth;

/**
 * Api 状态码 配置
 */
public enum ApiCode {
    //--------------------------------------------常规通用--------------------------------------------------
    SUCCESS(200),
    FAIL(1000),
    PARAM_MISSING(1001),
    PARAM_ERROR(1002),
    SIGNATURE_ERROR(1003),
    //--------------------------------------------注册登录--------------------------------------------------
    USERNAME_ERROR(2004),
    PWD_FORMAT_ERROR(2006),
    USER_EXIST_ERROR(2007),
    USER_NOEXIST_ERROR(2008),
    TOKEN_ERROR(2010),
    TOKEN_EXPIRE_ERROR(2011),
    USER_PWD_ERROR(2012),
    USER_UNAUTHENTICATED_ERROR(2013),
    USER_ACCESS_DENIED_EROOR(2014),
    AUTH_CODE_INVALID_ERROR(2015),
    PWD_ERROR(2016),

    /**
     * 所有没有特殊处理的错误,都归为未知错误
     */
    UNKNOWN_ERROR(9000);

    private int code;

    ApiCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return String.valueOf(code);
    }


}
