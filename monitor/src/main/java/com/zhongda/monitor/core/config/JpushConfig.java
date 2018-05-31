package com.zhongda.monitor.core.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 
 * Title: 极光推送
 *
 * Description:推送给移动端数据
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年5月28日 下午3:51:34
 */
public class JpushConfig {

	public static final Logger logger = LoggerFactory
			.getLogger(JpushConfig.class);

	private static final String JPUSH_APPKEY = "007c1936d3121195209f05a0";// appkey

	private static final String JPUSH_MASTERSECRET = "3c49acb2b65bd1d614fcfad8";// MasterSecret

	/**
	 * 安卓推送
	 * 
	 * @param parm
	 */
	public static void jpushAndroid(Map<String, String> parm) {
		JPushClient jPushClient = new JPushClient(JPUSH_MASTERSECRET,
				JPUSH_APPKEY);
		PushPayload payload = PushPayload
				.newBuilder()
				// 指定android平台的用户
				.setPlatform(Platform.android())
				// 你项目中的所有用户
				.setAudience(Audience.all())
				// abc
				// 发送内容
				.setNotification(
						Notification.android(parm.get("msg"), "您有新数据", parm))
				.build();
		try {
			PushResult result = jPushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			// Connection error, should retry later
			logger.error("连接错误，应该稍后重试!", e);
		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			logger.error("应该检查错误并修复请求！", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}

	}
}
