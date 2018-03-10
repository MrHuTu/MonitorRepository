package com.zhongda.monitor.business.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.zhongda.monitor.business.controller.ItemController;
import com.zhongda.monitor.business.model.ItemAvgData;
import com.zhongda.monitor.business.model.WiselyResponse;
import com.zhongda.monitor.business.service.impl.ItemServiceImpl;
import com.zhongda.monitor.core.service.WebSocketService;

/**
 * 
 * @author huchao 2018年3月7日11:51:38
 * 项目日均值实时数据推送   
 */
//@Component
public class PushItemAvgData {
	@Autowired
	ItemServiceImpl ItemServiceImpl;
	
	@Autowired
	WebSocketService webSocketService;
	
	
	/**
	 * 这是一个定时任务，基于/getItemAvgData这个请求 对全局变量userMsg里的所有数据进行遍历
	 * 向发起了该请求的用户提供指定项目的日均值数据
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedRate = 1000)
	public void callback() throws Exception {	
		HashMap<String, String> userMsg = ItemController.userMsg;
		//用在测试的2个用户
		userMsg.put("huchaoA", "176");
		userMsg.put("huchaoB", "200");
		Iterator<Entry<String, String>> map = userMsg.entrySet().iterator();
		while (map.hasNext()) {
			Entry<String, String> Entry = map.next();
			String userid = Entry.getKey();
			String myPoJoId = Entry.getValue();
			List<ItemAvgData> itemAvgData = ItemServiceImpl.selectItemAvgDataByPojoId(myPoJoId);
			webSocketService.send2Users(userid, new WiselyResponse(itemAvgData));
		}
		
	}
}
