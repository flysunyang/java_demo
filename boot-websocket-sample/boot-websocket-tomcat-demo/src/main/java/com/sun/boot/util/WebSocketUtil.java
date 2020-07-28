package com.sun.boot.util;

import com.alibaba.fastjson.JSONObject;
import com.sun.boot.message.Message;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoyang
 * @description Session 会话的管理
 * 多种发送消息的方式
 * @create 2020-08-10 15:56
 */
@Slf4j
public class WebSocketUtil {

    /**
     * Session 与用户的映射
     */
    private static final Map<Session, String> SESSION_USER_MAP = new ConcurrentHashMap<>();

    /**
     * 用户与 Session 的映射
     */
    private static final Map<String, Session> USER_SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 添加 Session ，添加用户和 Session 之间的映射
     * @param session
     * @param user
     */
    public static void addSession(Session session, String user) {
        USER_SESSION_MAP.put(user, session);
        SESSION_USER_MAP.put(session, user);
    }

    /**
     * 移除 Session
     * @param session
     */
    public static void removeSession(Session session) {
        String user = SESSION_USER_MAP.remove(session);
        if(user != null && user.length() > 0) {
            USER_SESSION_MAP.remove(user);
        }
    }

    /**
     * 广播发送消息给所有在线用户
     * @param type 消息类型
     * @param message 消息体
     * @param <T>
     */
    public static <T extends Message> void broadcast(String type, T message) {
        String messageText = buildTextMessage(type, message);
        for (Session session : SESSION_USER_MAP.keySet()) {
            sendTextMessage(session, messageText);
        }
    }

    /**
     * 发送消息给单个用户 Session
     * @param session Session
     * @param type 消息类型
     * @param message 消息体
     * @param <T>
     */
    public static <T extends Message> void send(Session session, String type, T message) {
        String messageText = buildTextMessage(type, message);
        sendTextMessage(session, messageText);
    }

    /**
     * 发送消息给指定用户
     * @param user 指定用户
     * @param type 消息类型
     * @param message 消息体
     * @param <T>
     * @return 是否发送成功
     */
    public static <T extends Message> boolean send(String user, String type, T message) {
        Session session = USER_SESSION_MAP.get(user);
        if(session == null) {
            log.error("[send][user({}) 不存在对应的 session]", user);
            return false;
        }
        // 发送消息
        send(session, type, message);
        return true;
    }

    /**
     * 构建完整的消息
     * @param type 消息类型
     * @param message 消息体
     * @param <T>
     * @return 消息
     */
    private static <T extends Message> String buildTextMessage(String type, T message) {
        JSONObject messageObject = new JSONObject();
        messageObject.put("type", type);
        messageObject.put("body", message);
        return messageObject.toJSONString();
    }

    /**
     * 真正发送消息
     * @param session Session
     * @param messageText 消息
     */
    private static void sendTextMessage(Session session, String messageText) {
        if(session == null) {
            log.error("[sendTextMessage][session 为 null]");
        }
        RemoteEndpoint.Basic basic = session.getBasicRemote();
        try {
            basic.sendText(messageText);
        } catch (IOException e) {
            log.error("[sendTextMessage][session({}) 发送消息{}) 发生异常",
                    session, messageText, e);
        }
    }
}
