package com.sun.boot.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhaoyang
 * @description 认证请求
 * @create 2020-07-28 18:36
 */
@Data
@Accessors(chain = true)
public class AuthRequest implements Message {

    public static final String TYPE = "AUTH_REQUEST";

    private String accessToken;

}
