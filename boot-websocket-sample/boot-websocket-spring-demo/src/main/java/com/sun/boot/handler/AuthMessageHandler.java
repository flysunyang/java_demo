package com.sun.boot.handler;

import com.sun.boot.message.AuthRequest;
import com.sun.boot.message.AuthResponse;
import com.sun.boot.message.UserJoinNoticeResponse;
import com.sun.boot.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-28 18:42
 */
@Component
@Slf4j
public class AuthMessageHandler implements MessageHandler<AuthRequest> {

    @Override
    public String type() {
        return AuthRequest.TYPE;
    }

    @Override
    public void execution(WebSocketSession webSocketSession, AuthRequest authRequest) {
        String accessToken = authRequest.getAccessToken();
        if(StringUtils.isEmpty(accessToken)) {
            log.warn("[AuthMessageHandler][webSocketSession({}) 未传入 accessToken]", webSocketSession);
            WebSocketUtil.send(webSocketSession, AuthResponse.TYPE,
                    new AuthResponse().setCode(1).setMessage("认证 accessToken 未传入"));
        }
        WebSocketUtil.addSession(webSocketSession, accessToken);
        WebSocketUtil.send(webSocketSession, AuthResponse.TYPE,
                new AuthResponse().setCode(0).setMessage("认证成功"));
        WebSocketUtil.broadcast(UserJoinNoticeResponse.TYPE, new UserJoinNoticeResponse().setNickname(accessToken));
    }

}
