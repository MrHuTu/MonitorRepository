package com.zhongda.monitor.business.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.utils.ShiroUtils;

@Controller
public class WebSocketBusinessController {

	@MessageMapping("/change")
	@SendTo("/topic/notice")
	public String gretting(String value) {
		System.out.println("wesd----------------------");
		return value;
	}

	@MessageMapping(value = "/linkok")
	@SendTo("/topic/notice")
	public Integer link(String token) {
		System.out.println("link:" + token);

		User user = ShiroUtils.getUserFromToken(token);
		System.out.println("testUserId:" + user.getUserId());
		return user.getUserId();
	}

	@RequestMapping(value = "/test")
	public void test(HttpServletResponse response) throws IOException {
		System.err.println("------------------------------");
		response.getWriter().print(1);
	}

}
