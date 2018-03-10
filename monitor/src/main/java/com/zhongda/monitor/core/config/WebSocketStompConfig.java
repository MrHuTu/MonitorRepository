package com.zhongda.monitor.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 *<p>stomp websocket的子协议，stomp: simple/streaming text oriented message protocol. 简单/流 文本消息协议</p>
 * 使用activeMQ中间件服务器
 * @author zmdeng
 * @date 2017年8月30日
 */
@Configuration
// 开启对websocket的支持,使用stomp协议传输代理消息
@EnableWebSocketMessageBroker
public class WebSocketStompConfig extends
		AbstractWebSocketMessageBrokerConfigurer {

	public static final Logger logger = LoggerFactory.getLogger(WebSocketStompConfig.class);

	/**
	 * 连接的端点，客户端建立连接时需要连接这里配置的端点
	 * 将"/webSocket"路径注册为STOMP端点，这个路径与发送和接收消息的目的路径有所不同，
	 * 这是一个端点，客户端在订阅或发布消息到目的地址前，要连接该端点，
	 * 即用户发送请求url="/applicationName/webSocket"与STOMP server进行连接。之后再转发到订阅url；
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 注册websocket，客户端用ws://host:port/项目名/webSocket 访问
		//为js客户端提供连接,客户端需要注册这个端点进行链接
		registry.addEndpoint("/webSocket").setAllowedOrigins("*")
				.withSockJS();// 表示支持以SockJS方式连接服务器,提供浏览器兼容性
	}

	/**
	 * 配置消息代理
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		logger.debug("加载websocket！");
		// 设置服务器广播消息的基础路径,这句话表示在topic和queue这两个域上服务端可以向客户端发消息
		// enableStompBrokerRelay是代理前缀，而返回的消息将会路由到代理上，所有订阅该代理的将收到响应的消息。
//		registry.enableStompBrokerRelay("/topic", "/queue")
//				.setRelayHost("192.168.1.101").setRelayPort(61613)
//				.setClientLogin("admin").setClientPasscode("admin");
		registry.enableSimpleBroker("/topic","/user");
		// 设置客户端订阅消息的基础路径,这句话表示客户端向服务器端发送时的主题上面需要加"/ws"作为前缀
		// applicationDestinationPrefixes应用前缀，所有请求的消息将会路由到@MessageMapping的controller上
		registry.setApplicationDestinationPrefixes("/ws");
		// 这句话表示服务端给客户端指定用户发送一对一的主题，前缀是"/user"
		registry.setUserDestinationPrefix("/user");
	}
}