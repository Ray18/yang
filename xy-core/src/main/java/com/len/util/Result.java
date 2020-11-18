package com.len.util;

import lombok.Data;

/**
 * @className: Result
 * @Description: 更新版本 ajax回执类  result风格
 * @author:by yangtianfeng
 * @classDate: 2020/8/14 9:28
 * @Version: 1.0
 **/
@Data
public final class Result<T> {

    private int code;

    //新增状态码 默认为true
    private boolean flag = true;

    private String msg = "success";

    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setData(data);

        return result;
    }

    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setCode(200);

        return result;
    }

    public static <T> Result<T> ok(int code) {
        Result<T> result = new Result<>();
        result.setCode(code);

        return result;
    }

    public static <T> Result<T> ok(int code, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);

        return result;
    }

    public static <T> Result<T> ok(int code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String error) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg(error);
        result.setFlag(false);
        return result;
    }

    public static <T> Result<T> error(String error, T data) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg(error);
        result.setData(data);
        result.setFlag(false);
        return result;
    }

    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg("月球见");
        result.setFlag(false);

        return result;
    }

    public static <T> Result<T> error(int code, String error) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(error);
        result.setFlag(false);
        return result;
    }

    public static <T> Result<T> error(int code, String error, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(error);
        result.setData(data);
        result.setFlag(false);
        return result;
    }

}
