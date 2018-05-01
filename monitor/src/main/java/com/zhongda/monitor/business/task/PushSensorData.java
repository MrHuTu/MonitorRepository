package com.zhongda.monitor.business.task;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * Title: 传感器定时任务
 *
 * Description:处理实时监控数据
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年4月26日 上午9:26:07
 */
@Component
public class PushSensorData {

	@Resource
	private SimpMessagingTemplate template;

	public static ConcurrentHashMap<String, HashSet<String>> concurrentHashMap = new ConcurrentHashMap<String, HashSet<String>>();

	@Scheduled(cron = "0 0/1 * * * ?")
	public void push() {
		if (null != concurrentHashMap && concurrentHashMap.size() > 0) {
			for (Map.Entry<String, HashSet<String>> entry : concurrentHashMap
					.entrySet()) {
				template.convertAndSendToUser(entry.getKey(), "/test",
						entry.getKey() + "你好呀!");
			}
		}
	}

}
