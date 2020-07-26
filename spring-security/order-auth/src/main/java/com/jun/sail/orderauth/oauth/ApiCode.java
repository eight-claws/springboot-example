package com.jun.sail.orderauth.oauth;


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
    MOBILE_ERROR(2002),
    EMAIL_ERROR(2003),
    USERNAME_ERROR(2004),
    SMSCODE_ERROR(2005),
    PWD_FORMAT_ERROR(2006),
    USER_EXIST_ERROR(2007),
    USER_NOEXIST_ERROR(2008),
    COUNTRY_NOEXIST_ERROR(2009),
    TOKEN_ERROR(2010),
    TOKEN_EXPIRE_ERROR(2011),
    USER_PWD_ERROR(2012),
    USER_UNAUTHENTICATED_ERROR(2013),
    USER_ACCESS_DENIED_EROOR(2014),
    AUTH_CODE_INVALID_ERROR(2015),
    PWD_ERROR(2016),
    NEW_AND_OLD_PWD_IDENTICAL_ERROR(2017),
    EMAIL_CODE_ERROR(2018),
    EMAIL_TEMPLATE_NOT_EXIST_ERROR(2019),
    MOBILE_ALREADY_BOUND_ERROR(2020),
    EMAIL_ALREADY_BOUND_ERROR(2021),
    MOBILE_NOT_EXIST_ERROR(2022),
    EMAIL_NOT_EXIST_ERROR(2023),
    COUNTRY_INCONSISTENT_ERROR(2024),
    NO_UNBOUND_MOBILE_ERROR(2025),
    NO_UNBOUND_EMAIL_ERROR(2026),

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
