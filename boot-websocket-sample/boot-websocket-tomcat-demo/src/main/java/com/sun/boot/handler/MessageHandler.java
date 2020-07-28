package com.sun.boot.handler;

import com.sun.boot.message.Message;

import javax.websocket.Session;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-28 11:00
 */
public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     * @param session 会话
     * @param t 消息
     */
    void execute(Session session, T t);

    /**
     * 消息类型，即每个 Message 实现类上的 TYPE 静态字段
     * @return
     */
    String getType();
}
