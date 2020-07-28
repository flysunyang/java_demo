package com.sun.boot.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-28 10:51
 */
@Data
@Accessors(chain = true)
public class AuthResponse implements Message {

    public static final String TYPE = "AUTH_RESPONSE";

    private Integer code;

    private String message;

}
