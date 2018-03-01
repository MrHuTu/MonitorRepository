package com.zhongda.monitor.core.utils.sms;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhongda.monitor.core.utils.sms.SmsSenderResult.SmsMultiSenderResult;
import com.zhongda.monitor.core.utils.sms.SmsSenderResult.SmsSingleSenderResult;

/**
 * 短信发送器
 * @author zmdeng
 */
public class SmsSender {
	
	public static final Logger logger = LoggerFactory.getLogger(SmsSender.class);
	
	private TimSmsSender timSmsSender = new TimSmsSender();
	
	/**
	 * 指定模板向单个用户发送短信
	 * 假设短信模板 id 为 49685，模板内容为：你的验证码是{1}，{2}分钟内有效。如非本人操作，请忽略本短信。
	 * 则params参数按顺序填充{1},{2}
	 * @param tmplId 模板Id
	 * @param phoneNumber 用户手机号
	 * @param params 填充模板占位符的参数,以ArrayList集合装载
	 * @return
	 */
	public SmsSingleSenderResult send(int tmplId, String phoneNumber, List<String> params){
		SmsSingleSenderResult singleSenderResult = null;
		try {
			singleSenderResult = timSmsSender.sendWithParam("86", phoneNumber, tmplId, params, "", "", "");
		} catch (Exception e) {
			logger.error(singleSenderResult.toString());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info(singleSenderResult.toString());
		return singleSenderResult;
	}
	
	/**
	 * 指定模板向多个用户发送短信
	 * 假设短信模板 id 为 49685，模板内容为：你的验证码是{1}，{2}分钟内有效。如非本人操作，请忽略本短信。
	 * 则params参数按顺序填充{1},{2}
	 * @param tmplId 模板Id
	 * @param phoneNumbers 多个用户的手机号,以ArrayList集合装载
	 * @param params 填充模板占位符的参数,以ArrayList集合装载
	 * @return
	 */
	public SmsMultiSenderResult send(int tmplId, List<String> phoneNumbers, List<String> params){
		SmsMultiSenderResult multiSenderResult = null;
		try {
			multiSenderResult = timSmsSender.sendWithParam("86", phoneNumbers, tmplId, params, "", "", "");
		} catch (Exception e) {
			logger.error(multiSenderResult.toString());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info(multiSenderResult.toString());
		return multiSenderResult;
	}
		
}
