package com.zhongda.monitor.core.utils.sms;

public class SmsContentTemplate {
	
	/**
	 * 验证码模板Id : 49685  
	 * 模板内容 : 你的验证码是{1}，{2}分钟内有效。如非本人操作，请忽略本短信。
	 */
	public static final int VerifyCode = 49685;
	/**
	 * 数据类告警模板Id : 57454  
	 * 模板内容 : 尊敬的(账号：{1})客户：您的项目于{2}监测到{3}数据超过阈值，项目名称：{4}，采集终端：{5}，传感器编号： {6}，当前值{7}，正常值范围{8}~{9}。详情请登陆中大检测在线监测服务平台查看。谢谢 ！
	 */
	public static final int ALARM_DATA_MESSAGE = 57454;
	/**
	 * 设备类告警模板Id : 57453  
	 * 模板内容 : 尊敬的(账号：{1})客户：您好！您的项目于{2}监测到{3}设备类告警信息。项目名称：{4}，采集器终端： {5}， 传感器编号：{6}，{7}，详情请点击中大检测在线检测服务平台查看，谢谢！
	 */
	public static final int ALARM_DEVICE_MESSAGE = 57453;
	/**
	 * 验证码模板Id : 96496  
	 * 模板内容 : 您正在进行登录密码重置操作，验证码为{1}，请在{2}分钟内按页面提示提交验证码，切勿将验证码泄露于他人。
	 */
	public static final int ResetVerifyCode = 96496;
	
}
