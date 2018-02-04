package com.zhongda.monitor.core.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebSocketController {

	@Resource
	public SimpMessagingTemplate template;

	@MessageMapping("/message")
	// 由客户端根据路径/ws/message发送的消息会路由至该注解所映射的方法(ws是隐含的)
	@SendTo("/topic/hello")
	// 如果没有该注解，那么该方法会将返回的值默认路由至/topic/message目的地,如果有该注解则会路由至注解中值得所在目的地
	public String userMessage(@RequestBody Map<String, Object> userMessage)
			throws Exception {
		System.out.println("message");
		// template.convertAndSend("/topic/hello",
		// userMessage.get("taskName")+":123456");
		System.out.println("message:" + userMessage.get("name"));
		return userMessage.get("taskDetail").toString();
	}

	@MessageMapping("/singlemessage")
	// 由客户端根据路径/ws/message发送的消息会路由至该注解所映射的方法(ws是隐含的)
	@SendToUser("/queue/single")
	// 该注解则会路由至注解中值得所在目的地
	public String oneUserMessage(@RequestBody Map<String, Object> userMessage)
			throws Exception {
		System.out.println("message");
		// template.convertAndSend("/topic/hello",
		// userMessage.get("taskName")+":123456");
		System.out.println("message:" + userMessage.get("name"));
		return userMessage.get("taskDetail").toString();
	}

}