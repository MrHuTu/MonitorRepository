package com.zhongda.monitor.business.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.business.model.AlarmLinkman;
import com.zhongda.monitor.business.service.AlarmLinkmanService;
import com.zhongda.monitor.core.service.MailService;
import com.zhongda.monitor.core.utils.MailUtils;
import com.zhongda.monitor.core.utils.sms.SmsContentTemplate;
import com.zhongda.monitor.core.utils.sms.SmsSender;


/**
 * 告警信息推送
 * 
 * @author KCheng
 *
 */
@Component
public class PushAlarm {

	@Resource
	private MailService mailService;
	@Resource
	private AlarmLinkmanService alarmLinkmanService;
	
	@Scheduled(cron = "0 0/10 * * * ?")
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
					mailService.sendHtmlMail(/*管理员邮箱账号*/"771588005@qq.com", "告警",
							alarmLinkman.getProjectName() + "没有对应的告警联系人");
				} else {// 有告警联系人
					emailList.add(/*alarmLinkman.getEmail()*/"771588005@qq.com");
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
								
								smsSender.send(SmsContentTemplate.ALARM_DATA_MESSAGE,/*alarmLinkman.getPhone()*/"13348658366", params);
							}
							if (alarmLinkman.getAlarmType() == 1) {// 如果是设备告警
								params.add(alarmLinkman.getAlarmContext());
								mailService.sendBatchHtmlTemplateMail(
										emailList, MailUtils.ALARM_SUBJECT,
										MailUtils.DEVICE_ALARM_TEMPLATE,
										paramsMap);
								smsSender.send(SmsContentTemplate.ALARM_DEVICE_MESSAGE,/*alarmLinkman.getPhone()*/"13348658366", params);
							}
						}
					}.start();

				}
			}
		}

	}
}
