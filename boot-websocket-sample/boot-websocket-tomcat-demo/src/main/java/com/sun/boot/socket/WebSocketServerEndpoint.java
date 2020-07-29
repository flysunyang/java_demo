package com.sun.boot.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.boot.handler.MessageHandler;
import com.sun.boot.message.AuthRequest;
import com.sun.boot.message.Message;
import com.sun.boot.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhaoyang
 * @description InitializingBean 接口为 bean 提供了初始化方法的方式，它只包括 afterPropertiesSet 方法，
 * 凡是继承该接口的类，在初始化 bean 的时候会执行该方法。
 * @create 2020-07-28 10:35
 */
@Controller
@ServerEndpoint("/")
@Slf4j
public class WebSocketServerEndpoint implements InitializingBean {

    /**
     * 消息类型与 MessageHandler 的映射
     * <p>
     * 注意，这里设置成静态变量。虽然说 WebSocketServerEndpoint 是单例，但是 Spring Boot 还是会为每个 WebSocket 创建一个 WebSocketServerEndpoint Bean 。
     */
    private static final Map<String, MessageHandler> HANDLERS = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    @OnOpen
    public void onOpen(Session session) {
        log.info("[onOpen][session({}) 接入]", session);
        // 1.解析accessToken
        List<String> accessTokenList = session.getRequestParameterMap().get("accessToken");
        String accessToken = !CollectionUtils.isEmpty(accessTokenList) ? accessTokenList.get(0) : null;
        // 2.创建AuthRequest 消息类型
        AuthRequest authRequest = new AuthRequest().setAccessToken(accessToken);
        // 3.获取消息处理器
        MessageHandler messageHandler = HANDLERS.get(AuthRequest.TYPE);
        if (messageHandler == null) {
            log.error("[onOpen][认证消息类型，不存在AuthRequest类型的消息处理器]");
        }
        messageHandler.execute(session, authRequest);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("[onMessage][session({}) 接收到一条消息({})]", session, message);
        // 1.获取消息类型
        JSONObject jsonMessage = JSON.parseObject(message);
        String messageType = jsonMessage.getString("type");
        // 2.获取消息处理器
        MessageHandler messageHandler = HANDLERS.get(messageType);
        if (messageHandler == null) {
            log.error("[onMessage][消息类型({})，不存在消息处理器]", messageType);
        }
        // 3.解析消息
        Class<? extends Message> messageClass = this.getMessageClass(messageHandler);
        // 4.处理消息
        Message messageObj = JSON.parseObject(jsonMessage.getString("body"), messageClass);
        messageHandler.execute(session, messageObj);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        log.info("[onClose][session({}) 连接关闭。关闭原因是({})}]", session, closeReason);
        WebSocketUtil.removeSession(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("[onClose][session({}) 发生异常]", session, throwable);
    }

    private Class<? extends Message> getMessageClass(MessageHandler handler) {
        // 获得 Bean 对应的 Class 类名。因为有可能被 AOP 代理过。
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        // 获得接口的 Type 数组
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        // 此处，是以父类的接口为准
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)) {
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (Objects.nonNull(interfaces)) {
            // 遍历 interfaces 数组
            for (Type type : interfaces) {
                // 要求 type 是泛型参数
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    // 要求是 MessageHandler 接口
                    if (Objects.equals(parameterizedType.getRawType(), MessageHandler.class)) {
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        // 取首个元素
                        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                            return (Class<Message>) actualTypeArguments[0];
                        } else {
                            throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
    }

    /**
     * 每次初始化 WebSocketServerEndpoint bean 的时候，都会执行 afterPropertiesSet 方法
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 获得所有 MessageHandler Bean，添加到 handlers 中
        applicationContext.getBeansOfType(MessageHandler.class).values()
                .forEach(messageHandler -> HANDLERS.put(messageHandler.getType(), messageHandler));
        log.info("[afterPropertiesSet][消息处理器数量: {}]", HANDLERS.size());
    }
}
