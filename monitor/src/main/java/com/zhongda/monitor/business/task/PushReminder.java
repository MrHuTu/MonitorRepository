package com.zhongda.monitor.business.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.business.model.Reminder;
import com.zhongda.monitor.business.service.ReminderService;
import com.zhongda.monitor.core.service.MailService;


/**
 * 告警信息推送
 * @author KCheng
 */
@Component
public class PushReminder {
	
	private static final Logger logger = LoggerFactory.getLogger(PushReminder.class);
	
	@Resource
	private MailService mailService;
	
	@Resource
	private ReminderService reminderService;
	
	@Scheduled(cron = "0 5/10 * * * ?")
	public void sendMessage() {
		logger.info("开始推送提醒消息");
		List<String> emails = null;
		List<Reminder> reminderList = reminderService.selectReminderPeriod();	
		if(null != reminderList && reminderList.size() > 0){
			logger.info("推送提醒消息" + reminderList.size() + "条");
			emails = new ArrayList<String>();
			emails.add("731583657@qq.com");
			emails.add("1250368725@qq.com");
			emails.add("652421757@qq.com");
		}
		for (Reminder reminder : reminderList) {
			mailService.sendBatchHtmlMail(emails, "提醒",
					reminder.getReminderContext() + "---对应终端编号，终端通道号，传感器编号分别为：" + reminder.getSmuNumber() + "," + reminder.getSmuChannel() + "," + reminder.getSensorNumber());
		}
	}
}
