package com.zhongda.monitor;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MonitorApplicationTests {
	
	@Autowired
    private JavaMailSender sender;
	
	@Test
	public void contextLoads() throws MessagingException {
		System.out.println("into sendSimpleMail");
		System.out.println(sender);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("zdjcyun@hnzdjt.com.cn");
		message.setTo("731583657@qq.com");
		message.setSubject("11222");
		message.setText("3455");
		sender.send(message);
	}

}
