package com.sun.boot.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-28 10:58
 */
@Data
@Accessors(chain = true)
public class SendResponse implements Message {

    public static final String TYPE = "SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应提示
     */
    private String message;

}
