package com.jun.sail.springmvc.web.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class RestResult<T> implements Serializable {

    private static final long serialVersionUID = 6095433538316185017L;

    private int code;

    private String message;

    private T data;

    public RestResult() {
    }

    public RestResult(int code, String message, T data) {
        this.code = code;
        this.setMessage(message);
        this.data = data;
    }

    public RestResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public RestResult(int code, String message) {
        this.code = code;
        this.setMessage(message);
    }

    public boolean ok() {
        return this.code == 0 || this.code == 200;
    }

    /**
     * 这里有一些简化，没有继承父类或接口，建造者模式实际可以有不同的实现子类
     */
    public static <T> ResResultBuilder<T> builder() {
        return new ResResultBuilder<T>();
    }

    /**
     * 建造者模式的体现
     * @param <T>
     */
    public static final class ResResultBuilder<T> {

        private int code;

        private String errMsg;

        private T data;

        private ResResultBuilder() {
        }

        public ResResultBuilder<T> withCode(int code) {
            // 当然这里可以再添加一些复杂的逻辑，比如设置了某属性值，其他属性就只能为某些值
            this.code = code;
            return this;
        }

        public ResResultBuilder<T> withMsg(String errMsg) {
            this.errMsg = errMsg;
            return this;
        }

        public ResResultBuilder<T> withData(T data) {
            this.data = data;
            return this;
        }

        /**
         * Build result.
         *
         * @return result
         */
        public RestResult<T> build() {
            RestResult<T> restResult = new RestResult<T>();
            restResult.setCode(code);
            restResult.setMessage(errMsg);
            restResult.setData(data);
            return restResult;
        }
    }
}