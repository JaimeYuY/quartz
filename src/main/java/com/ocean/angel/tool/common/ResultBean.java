package com.ocean.angel.tool.common;

import com.ocean.angel.tool.constant.ResultCode;
import lombok.Data;

/**
 * 响应类
 */
@Data
public class ResultBean<T> {

    // 响应码
    private Integer code;

    // 提示信息
    private String msg;

    // 响应数据
    private T data;

    public ResultBean(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultBean<T> success(T data) {
        return new ResultBean(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static <T> ResultBean<T> success() {
        return new ResultBean(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null);
    }

    public static <T> ResultBean<T> error() {
        return new ResultBean(ResultCode.SERVICE_INTERNAL_ERROR.getCode(), ResultCode.SERVICE_INTERNAL_ERROR.getMsg(), null);
    }

    public static <T> ResultBean<T> error(ResultCode messageCode) {
        return new ResultBean(messageCode.getCode(), messageCode.getMsg(), null);
    }
}
