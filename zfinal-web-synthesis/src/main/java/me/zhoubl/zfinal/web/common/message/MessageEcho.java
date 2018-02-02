package me.zhoubl.zfinal.web.common.message;

import me.zhoubl.zfinal.common.utils.serialization.FastJsonSerializer;
import me.zhoubl.zfinal.synthesis.api.SysMessageService;
import me.zhoubl.zfinal.synthesis.entity.SysMessage;
import me.zhoubl.zfinal.synthesis.enums.SysMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


/**
 * Created by zhoubl on 2017/6/18.
 */
@Component
public class MessageEcho {

    private static final Logger logger = LoggerFactory.getLogger(MessageEcho.class);

    @Autowired
    private SysMessageService sysMessageService;

    /**
     * 发送消息并持久化
     */
    @Async
    public void sendMessage(Long sysUserId, String title, String url) {
        try {
            SysMessage sysMessage = new SysMessage();
            sysMessage.setType(SysMessageType.DEFAULT.getVal());
            sysMessage.setSysUserId(sysUserId);
            sysMessage.setTitle(title);
            sysMessage.setUrl(url);
            sysMessageService.create(sysMessage);

            if (MessageWebSocketHandler.users != null) {
                WebSocketSession session = MessageWebSocketHandler.users.get(sysUserId);
                if (session != null && sysMessage != null && session.isOpen()) {
                    String message = FastJsonSerializer.toJSONString(sysMessage);
                    session.sendMessage(new TextMessage(message));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void logout(Long sysUserId, String title) {
        try {
            SysMessage sysMessage = new SysMessage();
            sysMessage.setType(SysMessageType.LOGOUT.getVal());
            sysMessage.setSysUserId(sysUserId);
            sysMessage.setTitle(title);

            if (MessageWebSocketHandler.users != null) {
                WebSocketSession session = MessageWebSocketHandler.users.get(sysUserId);
                if (session != null && sysMessage != null && session.isOpen()) {
                    String message = FastJsonSerializer.toJSONString(sysMessage);
                    session.sendMessage(new TextMessage(message));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public boolean isExistsConnect(Long sysUserId) {
        try {
            if (MessageWebSocketHandler.users != null) {
                WebSocketSession session = MessageWebSocketHandler.users.get(sysUserId);
                if (session != null) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }


}
