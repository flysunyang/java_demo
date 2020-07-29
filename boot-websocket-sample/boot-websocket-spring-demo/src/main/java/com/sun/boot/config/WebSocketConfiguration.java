package com.sun.boot.config;

import com.sun.boot.interceptor.DemoWebSocketShakeInterceptor;
import com.sun.boot.websocket.DemoWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author zhaoyang
 * @description 自定义配置类，处理 WebSocket ，实现 WebSocketConfigurer 接口
 * @create 2020-07-28 18:19
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        // 配置处理器
        webSocketHandlerRegistry.addHandler(this.demoWebSocketHandler(), "/")
                // 配置拦截器
                .addInterceptors(this.demoWebSocketShakeInterceptor())
                // 解决跨域问题
                .setAllowedOrigins("*");
    }

    @Bean
    public DemoWebSocketShakeInterceptor demoWebSocketShakeInterceptor() {
        return new DemoWebSocketShakeInterceptor();
    }

    @Bean
    public DemoWebSocketHandler demoWebSocketHandler() {
        return new DemoWebSocketHandler();
    }
}
