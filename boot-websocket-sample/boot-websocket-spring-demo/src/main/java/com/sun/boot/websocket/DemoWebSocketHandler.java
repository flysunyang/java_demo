package com.sun.boot.websocket;

import com.sun.boot.handler.MessageHandler;
import com.sun.boot.message.AuthRequest;
import com.sun.boot.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoyang
 * @description WebSocket 处理类
 * @create 2020-07-28 18:21
 */
@Slf4j
public class DemoWebSocketHandler extends TextWebSocketHandler implements InitializingBean {

    /**
     * 消息类型与 MessageHandler 的映射
     * <p>
     * 注意，这里设置成静态变量。虽然说 WebSocketServerEndpoint 是单例，但是 Spring Boot 还是会为每个 WebSocket 创建一个 WebSocketServerEndpoint Bean 。
     */
    private static final Map<String, MessageHandler> HANDLERS = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 对应 open 事件
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("[afterConnectionEstablished][WebSocketSession ({}) 接入]", webSocketSession);
        String accessToken = webSocketSession.getAttributes().get("accessToken").toString();
        AuthRequest authRequest = new AuthRequest().setAccessToken(accessToken);
        MessageHandler messageHandler = HANDLERS.get(AuthRequest.TYPE);
        if(messageHandler == null) {
            log.info("[open][认证消息类型，不存在消息处理器]");
        }
        messageHandler.execution(webSocketSession, authRequest);
    }

    /**
     * 对应 message 事件
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        log.info("[][]");
    }

    /**
     * 对应error事件
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        log.info("[handleTransportError][webSocketSession({}) 发生异常]", webSocketSession, throwable);
    }

    /**
     * 对应 close 事件
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("[afterConnectionClosed][webSocketSession({}) 连接关闭，关闭的原因是({})]", closeStatus);
        WebSocketUtil.removeSession(webSocketSession);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationContext.getBeansOfType(MessageHandler.class).values()
                .forEach(messageHandler -> HANDLERS.put(messageHandler.type(), messageHandler));
        log.info("[afterPropertiesSet][消息处理器数量: {}]", HANDLERS.size());
    }
}
