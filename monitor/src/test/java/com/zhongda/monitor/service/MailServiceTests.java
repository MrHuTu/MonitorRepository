package com.zhongda.monitor.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhongda.monitor.core.service.MailService;
import com.zhongda.monitor.core.utils.MailUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTests {

	@Autowired
	private MailService mailService;
	
	@Test
	public void sendSimpleMail2() {
		//发邮件验证码
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("realName","孔成");
		paramsMap.put("time", new SimpleDateFormat("yyyy年MM月dd日  HH小时mm分").format(new Date()) );
		paramsMap.put("userName", "kongcheng");
		paramsMap.put("code", "1111");
		mailService.sendHtmlTemplateMail("771588005@qq.com", MailUtils.CODE_SUBJECT, MailUtils.VALIDATE_CODE_TEMPLATE, paramsMap);
	}
	
	/*@Test
	public void sendSimpleMail() {
		mailService.sendSimpleMail("771588005@qq.com", "就笑一笑", "springboot I come Simple ");
	}*/
	
/*	@Test
	public void sendHtmlMail() {
		mailService.sendHtmlMail("771588005@qq.com", "就笑一笑", "springboot I come Html ");
	}*/
/*
	@Test
	public void sendAttachmentsMail() {
		mailService.sendAttachmentsMail("731583657@qq.com", "就笑一笑", "springboot I come Attachment", "D:\\YoudaoNote\\res\\attachmentIcon.png");
	}

	@Test
	public void sendInlineResourceMail() {
		mailService.sendInlineResourceMail("731583657@qq.com", "就笑一笑", "springboot I come ", "", "");
	}*/
	
}