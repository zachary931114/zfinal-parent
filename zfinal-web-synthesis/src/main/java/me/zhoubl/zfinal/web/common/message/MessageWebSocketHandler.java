package me.zhoubl.zfinal.web.common.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by zhoubl on 2017/6/18.
 */
public class MessageWebSocketHandler implements WebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(MessageWebSocketHandler.class);

    //websocket用户列表
    public static final Map<Long, WebSocketSession> users = new ConcurrentHashMap<Long, WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = getWebSocketUserId(session);
        if (userId != null) {
            if (users.containsKey(userId)){
                WebSocketSession wss = users.get(userId);
                if (wss.isOpen()) {
                    wss.close();
                }
            }
            users.put(userId, session);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        Long userId = getWebSocketUserId(session);
        if (userId != null) {
            users.remove(userId);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        Long userId = getWebSocketUserId(session);
        if (userId != null) {
            users.remove(userId);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private Long getWebSocketUserId(WebSocketSession session) {
        return (Long) session.getAttributes().get("WEBSOCKET_USER_ID");
    }
}
