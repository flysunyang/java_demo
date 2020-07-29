package com.sun.boot.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhaoyang
 * @description 用户加入提示响应
 * @create 2020-07-28 18:39
 */
@Data
@Accessors(chain = true)
public class UserJoinNoticeResponse implements Message {

    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    private String nickname;

}
