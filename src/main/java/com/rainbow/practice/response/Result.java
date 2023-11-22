package com.rainbow.practice.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private boolean success = false;

    private int code;

    private String message;

    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.success = true;
        result.code = 0;
        result.message = "OK";

        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.success = true;
        result.code = 0;
        result.message = "OK";
        result.data = data;

        return result;
    }

    public static <T> Result<T> failed(int code, String message) {
        Result<T> result = new Result<>();
        result.success = false;
        result.code = code;
        result.message = message;

        return result;
    }

    public static final class CommonErrors {
        public static final Result<Void> MissingRequestParams = Result.failed(900001, "缺失参数");
        public static final Result<Void> ParamsNotRecognized = Result.failed(900002, "无法识别的参数");
        public static final Result<Void> UnknownException = Result.failed(999999, "未知异常");
    }
}