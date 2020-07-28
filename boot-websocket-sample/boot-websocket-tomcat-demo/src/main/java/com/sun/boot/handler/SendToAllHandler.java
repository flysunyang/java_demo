package com.sun.boot.handler;

import com.sun.boot.message.SendResponse;
import com.sun.boot.message.SendToAllRequest;
import com.sun.boot.message.SendToUserRequest;
import com.sun.boot.util.WebSocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-28 16:42
 */
@Component
public class SendToAllHandler implements MessageHandler<SendToAllRequest> {

    @Override
    public void execute(Session session, SendToAllRequest message) {
        SendResponse sendResponse = new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        // 广播给所有人
        SendToAllRequest sendToAllRequest = new SendToAllRequest().setMsgId(message.getMsgId()).setContent(message.getContent());
        WebSocketUtil.broadcast(SendToUserRequest.TYPE, sendToAllRequest);
    }

    @Override
    public String getType() {
        return SendToAllRequest.TYPE;
    }
}
