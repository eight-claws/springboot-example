package com.jun.sail.springmvc.web.result;

public class RestResultUtils {

    public static <T> RestResult<T> success() {
        return RestResult.<T>builder().withCode(200).build();
    }

    public static <T> RestResult<T> success(T data) {
        return RestResult.<T>builder().withCode(200).withData(data).build();
    }

    public static <T> RestResult<T> success(T data, String msg) {
        return RestResult.<T>builder().withCode(200).withData(data).withMsg(msg).build();
    }

    public static <T> RestResult<T> success(int code, T data) {
        return RestResult.<T>builder().withCode(code).withData(data).build();
    }

    public static <T> RestResult<T> failed() {
        return RestResult.<T>builder().withCode(500).build();
    }

    public static <T> RestResult<T> failed(int code, T data) {
        return RestResult.<T>builder().withCode(code).withData(data).build();
    }

}