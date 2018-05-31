package com.zhongda.monitor.business.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.core.config.JpushConfig;

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

	// @Scheduled(cron = "0 2/10 * * * ?")
	public void pushAndroid() {
		System.out.println("定时器begin----------------");
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("msg", "当前检测指标已经更新数据，请及时查看！");
		JpushConfig.jpushAndroid(hashMap);
		System.out.println("定时器End----------------");
	}

}
