package com.sun.boot.util;

import com.sun.boot.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-28 18:16
 */
@Slf4j
public class WebSocketUtil {

    private static final Map<WebSocketSession, String> SESSION_USER_MAP = new ConcurrentHashMap<>();

    private static final Map<String, WebSocketSession> USER_SESSION_MAP = new ConcurrentHashMap<>();

    public static void addSession(WebSocketSession session, String user) {
        SESSION_USER_MAP.put(session, user);
        USER_SESSION_MAP.put(user, session);
    }

    public static void removeSession(WebSocketSession session) {
        String user = SESSION_USER_MAP.remove(session);
        if(!StringUtils.isEmpty(user)) {
            USER_SESSION_MAP.remove(user);
        }
    }

    public static <T extends Message> void broadcast(String type, T message) {
        TextMessage textMessage = buildTextMessage(type, message);
        for (WebSocketSession session : SESSION_USER_MAP.keySet()) {
            sendTextMessage(session, textMessage);
        }

    }

    public static <T extends Message> boolean send(String user, String type, T message) {
        WebSocketSession session = USER_SESSION_MAP.get(user);
        if(session == null) {
            log.error("[send][user({}) 不存在对应的 session]", user);
            return false;
        }
        send(session, type, message);
        return true;
    }

    public static <T extends Message> void send(WebSocketSession session, String type, T message) {
        TextMessage textMessage = buildTextMessage(type, message);
        sendTextMessage(session, textMessage);
    }

    public static <T extends Message> void sendTextMessage(WebSocketSession session, TextMessage textMessage) {
        if(session == null) {
            log.error("[sendTextMessage][session 为 null]");
        }
        try {
            session.sendMessage(textMessage);
        } catch (IOException e) {
            log.error("[sendTextMessage][session({}) 发送消息 ({})] 发生异常",
                    session, textMessage, e);
        }
    }

    public static <T extends Message> TextMessage buildTextMessage(String type, T message) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("type", type);
        map.put("body", message);
        return new TextMessage(map.toString());
    }

}
