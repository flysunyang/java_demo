package com.sun.boot.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhaoyang
 * @description 认证响应
 * @create 2020-07-28 18:38
 */
@Data
@Accessors(chain = true)
public class AuthResponse implements Message {

    public static final String TYPE = "AUTH_RESPONSE";

    private Integer code;

    private String message;

}
