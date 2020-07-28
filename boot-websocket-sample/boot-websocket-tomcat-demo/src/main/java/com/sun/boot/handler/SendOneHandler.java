package com.sun.boot.handler;

import com.sun.boot.message.SendResponse;
import com.sun.boot.message.SendToOneRequest;
import com.sun.boot.message.SendToUserRequest;
import com.sun.boot.util.WebSocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-28 16:36
 */
@Component
public class SendOneHandler implements MessageHandler<SendToOneRequest> {

    @Override
    public void execute(Session session, SendToOneRequest message) {
        SendResponse sendResponse = new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        SendToUserRequest sendToUserRequest = new SendToUserRequest().setMsgId(message.getMsgId()).setContent(message.getContent());
        WebSocketUtil.send(message.getToUser(), SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToOneRequest.TYPE;
    }
}
