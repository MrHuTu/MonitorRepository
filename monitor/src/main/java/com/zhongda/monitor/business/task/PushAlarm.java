package com.zhongda.monitor.business.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.business.model.Alarm;
import com.zhongda.monitor.business.model.AlarmLinkman;
import com.zhongda.monitor.business.service.AlarmService;
import com.zhongda.monitor.core.service.MailService;
import com.zhongda.monitor.core.utils.MailUtils;
import com.zhongda.monitor.core.utils.sms.SmsContentTemplate;
import com.zhongda.monitor.core.utils.sms.SmsSender;


/**
 * 告警信息推送
 * @author KCheng
 */
@Component
public class PushAlarm {
	
	private static final Logger logger = LoggerFactory.getLogger(PushAlarm.class);
	
	@Resource
	private MailService mailService;
	
	//@Resource
	//private AlarmLinkmanService alarmLinkmanService;
	
	@Resource
	private AlarmService alarmService;
	
	/*@Scheduled(cron = "0 5/10 * * * ?")
	public void sendMessage() {
		List<AlarmLinkman> akarmlList = alarmLinkmanService
				.selectAlarmLinkMan();
		List<String> emailList = new ArrayList<String>();
		Map<String, String> paramsMap = new HashMap<String, String>();
		List<String> params = new ArrayList<String>();
		if (akarmlList != null) {
			for (AlarmLinkman alarmLinkman : akarmlList) {
				// 如果沒有告警联系人
				if (alarmLinkman.getUserName() == null
						|| alarmLinkman.getEmail() == null
						|| alarmLinkman.getPhone() == null) {
					// 发送邮件给管理员
					mailService.sendHtmlMail(管理员邮箱账号"771588005@qq.com", "告警",
							alarmLinkman.getProjectName() + "没有对应的告警联系人");
				} else {// 有告警联系人
					emailList.add(alarmLinkman.getEmail()"771588005@qq.com");
					paramsMap.put("userName", alarmLinkman.getUserName());
					paramsMap.put("creatDate", alarmLinkman.getCreateTime()
							.toString());
					paramsMap.put("monitorType", alarmLinkman.getItemName());
					paramsMap.put("projectName", alarmLinkman.getProjectName());
					paramsMap.put("smuNumber", alarmLinkman.getSmuNumber()
							.toString());
					paramsMap.put("sensorNumber", alarmLinkman
							.getSensorNumber().toString());
					paramsMap.put("alarmContext",
							alarmLinkman.getAlarmContext());
					
					params.add(alarmLinkman.getUserName());
					params.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(alarmLinkman
							.getCreateTime()));
					params.add(alarmLinkman.getItemName());
					params.add(alarmLinkman.getProjectName());
					params.add(alarmLinkman.getSmuNumber().toString());
					params.add(alarmLinkman.getSensorNumber().toString());
					SmsSender smsSender = new SmsSender();
					
					new Thread() {
						public void run() {
							if (alarmLinkman.getAlarmType() == 2) {// 如果是数据告警
								String[] contextArray = alarmLinkman.getAlarmContext().split("，");
								params.add(contextArray[0].split("：")[1]);
								String[] thresholdArray = contextArray[2].split("~");
								params.add(thresholdArray[0].substring(5));
								params.add(thresholdArray[1]);
								mailService.sendBatchHtmlTemplateMail(
										emailList, MailUtils.ALARM_SUBJECT,
										MailUtils.DATA_ALARM_TEMPLATE,
										paramsMap);
								
								smsSender.send(SmsContentTemplate.ALARM_DATA_MESSAGE, alarmLinkman.getPhone(), params);
							}
							if (alarmLinkman.getAlarmType() == 1) {// 如果是设备告警
								params.add(alarmLinkman.getAlarmContext());
								mailService.sendBatchHtmlTemplateMail(
										emailList, MailUtils.ALARM_SUBJECT,
										MailUtils.DEVICE_ALARM_TEMPLATE,
										paramsMap);
								smsSender.send(SmsContentTemplate.ALARM_DEVICE_MESSAGE, alarmLinkman.getPhone(), params);
							}
						}
					}.start();

				}
			}
		}

	}*/
	
	@Scheduled(cron = "0 5/10 * * * ?")
	public void sendMessage() {
		logger.info("开始推送告警消息");
		List<Alarm> alarmList = alarmService.selectAlarmAndLinkmanPeriod();	
		if(null != alarmList && alarmList.size() > 0){
			logger.info("推送告警消息" + alarmList.size() + "条");
		}
		for (Alarm alarm : alarmList) {
			List<String> phoneNumbers = null;
			List<String> emails = null;
			List<String> paramsList = new ArrayList<String>();
			Map<String, String> paramsMap = new HashMap<String, String>();
			
			paramsMap.put("userName", alarm.getUserName());
			paramsMap.put("creatDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(alarm.getCreateTime()));
			paramsMap.put("monitorType", alarm.getMonitorTypeName());
			paramsMap.put("projectName", alarm.getProjectName());
			paramsMap.put("smuNumber", alarm.getSmuNumber());
			paramsMap.put("sensorNumber", alarm.getSensorNumber());
			
			paramsList.add(alarm.getUserName());
			paramsList.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(alarm.getCreateTime()));
			paramsList.add(alarm.getMonitorTypeName());
			paramsList.add(alarm.getProjectName());
			paramsList.add(alarm.getSmuNumber());
			paramsList.add(alarm.getSensorNumber());
			
			// 如果当前项目有对应的告警联系人
			if (null != alarm.getAlarmLinkmanList() && alarm.getAlarmLinkmanList().size() > 0) {
				phoneNumbers = new ArrayList<String>();
				emails = new ArrayList<String>();
				// 循环取出所有告警联系人的手机号码和邮箱地址
				for (AlarmLinkman alarmLinkman : alarm.getAlarmLinkmanList()) {
					phoneNumbers.add(alarmLinkman.getPhone());
					emails.add(alarmLinkman.getEmail());
				}
			} else { // 如果当前项目没有对应的告警联系人
				// 发送邮件给管理员
				mailService.sendHtmlMail("731583657@qq.com", "告警",
						alarm.getProjectName() + "没有对应的告警联系人");
			}
			SmsSender smsSender = new SmsSender();
			// 如果是数据类告警
			if (alarm.getAlarmType() == 2) {
				String[] contextArray = alarm.getAlarmContext().split("，");
				String[] currentData = contextArray[0].split("：");
				paramsMap.put("thresholdType", currentData[0]);
				paramsMap.put("currentData", currentData[1]);
				paramsList.add(currentData[0]);
				paramsList.add(currentData[1]);
				String[] thresholdArray = contextArray[2].split("~");
				String minNormal = thresholdArray[0].substring(5);
				paramsMap.put("minNormal", minNormal);
				paramsList.add(minNormal);
				paramsMap.put("maxNormal", thresholdArray[1]);
				paramsList.add(thresholdArray[1]);
				// 群发邮件
				mailService.sendBatchHtmlTemplateMail(emails, MailUtils.ALARM_SUBJECT, MailUtils.DATA_ALARM_TEMPLATE, paramsMap);
				// 群发短信
				smsSender.send(SmsContentTemplate.ALARM_DATA_MESSAGE, phoneNumbers, paramsList);
			} else if (alarm.getAlarmType() == 1) { // 如果是设备类告警
				paramsMap.put("alarmContext", alarm.getAlarmContext());
				paramsList.add(alarm.getAlarmContext());
				// 群发邮件
				mailService.sendBatchHtmlTemplateMail(emails, MailUtils.ALARM_SUBJECT, MailUtils.DEVICE_ALARM_TEMPLATE, paramsMap);
				// 群发短信
				smsSender.send(SmsContentTemplate.ALARM_DEVICE_MESSAGE, phoneNumbers, paramsList);
			}
		}
	}
}
