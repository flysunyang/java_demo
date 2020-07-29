package com.sun.boot.handler;

import com.sun.boot.message.Message;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-28 18:43
 */
public interface MessageHandler<T extends Message> {

    String type();

    void execution(WebSocketSession session, T t);
}
