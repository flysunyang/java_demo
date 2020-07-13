package com.sun.boot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-13 10:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 2011472222973898091L;

    private Integer code;

    private String message;

    private T t;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
