package com.sun.boot.vo;

import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-27 16:48
 */
@Data
public class CommonResult<T> implements Serializable {

    // 成功的码值
    private static final Integer SUCCESS_CODE = 0;

    private Integer code;

    private String message;

    private T t;

    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMessage());
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.state(!SUCCESS_CODE.equals(code), "必须是失败的码值");
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(code);
        commonResult.setMessage(message);
        return commonResult;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(SUCCESS_CODE);
        commonResult.setMessage("");
        commonResult.setT(data);
        return commonResult;
    }

}
