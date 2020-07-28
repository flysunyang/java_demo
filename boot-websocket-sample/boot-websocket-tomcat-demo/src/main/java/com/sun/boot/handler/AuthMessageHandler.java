package com.sun.boot.handler;

import com.sun.boot.message.AuthRequest;
import com.sun.boot.message.AuthResponse;
import com.sun.boot.message.UserJoinNoticeRequest;
import com.sun.boot.util.WebSocketUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.Session;

/**
 * @author zhaoyang
 * @description
 * @create 2020-08-10 15:52
 */
@Component
public class AuthMessageHandler implements MessageHandler<AuthRequest> {

    @Override
    public void execute(Session session, AuthRequest authRequest) {
        if(StringUtils.isEmpty(authRequest.getAccessToken())) {
            WebSocketUtil.send(session, AuthResponse.TYPE,
                    new AuthResponse().setCode(1).setMessage("认证 accessToken 未传入"));
        }
        // 添加到 WebSocketUtil 中
        WebSocketUtil.addSession(session, authRequest.getAccessToken());
        // 判断是否认证成功，这里模拟成功
        WebSocketUtil.send(session, AuthResponse.TYPE, new AuthResponse().setCode(0));
        // 通知所有人，某个人加入了，演示使用
        WebSocketUtil.broadcast(UserJoinNoticeRequest.TYPE, new UserJoinNoticeRequest().setNickname(authRequest.getAccessToken()));
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }
}
