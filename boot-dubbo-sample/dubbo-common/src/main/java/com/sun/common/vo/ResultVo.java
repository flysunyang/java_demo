package com.sun.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-14 18:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 5040848068492783447L;

    private Code code;

    private String message;

    private T t;

    public ResultVo(Code code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultVo success(Object object) {
        return new ResultVo(Code.OK200, "success", object);
    }

    public static ResultVo fail() {
        return new ResultVo(Code.FAIL500, "fail");
    }

    public static ResultVo fail(String message) {
        return new ResultVo(Code.FAIL500, message);
    }

    enum Code {
        OK200, FAIL500;
    }
}
