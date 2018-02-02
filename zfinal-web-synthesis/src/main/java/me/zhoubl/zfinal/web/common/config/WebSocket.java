package me.zhoubl.zfinal.web.common.config;

import me.zhoubl.zfinal.web.common.message.MessageHandshakeInterceptor;
import me.zhoubl.zfinal.web.common.message.MessageWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/**
 * Created by zhoubl on 2017/6/18.
 */
@EnableWebMvc
@EnableWebSocket
@Configuration
public class WebSocket implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
		webSocketHandlerRegistry.addHandler(messageWebSocketHandler(), "/admin/websocket/message")
				.setAllowedOrigins("*").addInterceptors(new MessageHandshakeInterceptor());
		webSocketHandlerRegistry.addHandler(messageWebSocketHandler(), "/admin/websocket/messageSockJs")
				.setAllowedOrigins("*").addInterceptors(new MessageHandshakeInterceptor()).withSockJS();
	}

	@Bean
	public WebSocketHandler messageWebSocketHandler() {
		return new MessageWebSocketHandler();
	}

}
