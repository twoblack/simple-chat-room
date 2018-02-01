package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//添加服务端点，接收客户端的连接
		registry.addEndpoint("/any-socket")
				//开启SockJS支持
				.withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//服务端接收地址的前缀
		registry.setApplicationDestinationPrefixes("/app");
		//客户端订阅地址的前缀信息
		registry.enableSimpleBroker("/topic","/user");
	}
	
	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
		registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
			
			@Override
			public WebSocketHandler decorate(WebSocketHandler handler) {
				return new WebSocketHandlerDecorator(handler){
					/* (non-Javadoc)
					 * 客户端与服务端建立连接后的服务端的操作
					 */
					@Override
					public void afterConnectionEstablished(WebSocketSession session) throws Exception {
						String username = session.getPrincipal().getName();
						System.out.println("online:"+username);
						super.afterConnectionEstablished(session);
					}
					
					/* (non-Javadoc)
					 * 客户端与服务端断开连接后服务端的操作
					 */
					@Override
					public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
							throws Exception {
						String username = session.getPrincipal().getName();
						System.out.println("offline:"+username);
						super.afterConnectionClosed(session, closeStatus);
					}
				};
			}
		});
	}

}
