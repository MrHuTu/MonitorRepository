package com.zhongda.monitor.core.service;

import java.util.List;
import java.util.Map;

public interface MailService {
	
	/**
	 * 发送纯文本的简单邮件
	 * @param to 接受方的邮箱地址
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	void sendSimpleMail(String to, String subject, String content);

	/**
	 * 发送html格式的邮件
	 * @param to 接受方的邮箱地址
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	void sendHtmlMail(String to, String subject, String content);
	
	/**
	 * 批量发送html格式的邮件（群发html格式的邮件）
	 * @param toList 所有接受方的邮箱地址（以List集合的形式存储）
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	void sendBatchHtmlMail(List<String> toList, String subject, String content);
	
	/**
	 * 发送html格式的模板邮件
	 * @param to 接受方的邮箱地址
	 * @param subject 邮件主题
	 * @param template 邮件模板标识
	 * @param params 填充模板占位符的参数
	 */
	void sendHtmlTemplateMail(String to, String subject, String template, Map<String, String> params);
	
	/**
	 * 批量发送html格式的模板邮件（群发html格式的模板邮件）
	 * @param toList 所有接受方的邮箱地址（以List集合的形式存储）
	 * @param subject 邮件主题
	 * @param template 邮件模板标识
	 * @param params 填充模板占位符的参数
	 */
	void sendBatchHtmlTemplateMail(List<String> toList, String subject, String template, Map<String, String> params);

	/**
	 * 发送带附件的邮件
	 * @param to 接受方的邮箱地址
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param filePath 附件地址
	 */
	void sendAttachmentsMail(String to, String subject, String content, String filePath);

	/**
	 * 发送嵌入静态资源（一般是图片）的邮件
	 * @param to 接受方的邮箱地址
	 * @param subject 邮件主题
	 * @param content 邮件内容  -- 需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
	 * @param rscPath 静态资源路径和文件名
	 * @param rscId 静态资源id
	 */
	void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}