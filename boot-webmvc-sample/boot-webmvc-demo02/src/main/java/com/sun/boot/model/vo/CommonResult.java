package com.sun.boot.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 15:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    // 成功的码值
    private static final Integer SUCCESS_CODE = 0;

    private Integer code;

    private String message;

    private T t;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(SUCCESS_CODE);
        commonResult.setMessage("");
        commonResult.setT(data);
        return commonResult;
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!SUCCESS_CODE.equals(code), "必须是失败的码值");
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(code);
        commonResult.setMessage(message);
        return commonResult;
    }

    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMessage());
    }

    /**
     * 忽略，避免 jackson 序列化给前端
     * 方便判断是否成功
     * @return
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}
