package me.zhoubl.zfinal.web.common.message;

import java.util.Map;

import me.zhoubl.zfinal.common.web.WebCommonConfig;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;


/**
 * Created by zhoubl on 2017/6/18.
 */
public class MessageHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(WebCommonConfig.USERINFO);
        attributes.put("WEBSOCKET_USER_ID", user.getId());
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}

