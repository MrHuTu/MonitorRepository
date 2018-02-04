package com.zhongda.monitor.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhongda.monitor.core.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTests {

	@Autowired
	private MailService mailService;

	@Test
	public void sendSimpleMail() {
		mailService.sendSimpleMail("731583657@qq.com", "就笑一笑", "springboot I come ");
	}

	@Test
	public void sendHtmlMail() {
		mailService.sendHtmlMail("731583657@qq.com", "就笑一笑", "springboot I come ");
	}

	@Test
	public void sendAttachmentsMail() {
		mailService.sendAttachmentsMail("731583657@qq.com", "就笑一笑", "springboot I come ", "");
	}

	@Test
	public void sendInlineResourceMail() {
		mailService.sendInlineResourceMail("731583657@qq.com", "就笑一笑", "springboot I come ", "", "");
	}
}