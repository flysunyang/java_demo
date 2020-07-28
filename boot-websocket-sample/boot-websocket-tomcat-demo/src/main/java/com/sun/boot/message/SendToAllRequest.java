package com.sun.boot.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-28 10:57
 */
@Data
@Accessors(chain = true)
public class SendToAllRequest implements Message {

    public static final String TYPE = "SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;

    /**
     * 内容
     */
    private String content;

}
