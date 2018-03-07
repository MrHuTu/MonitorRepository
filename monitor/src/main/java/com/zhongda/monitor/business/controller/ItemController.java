package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.utils.TokenUtils;
import com.zhongda.monitor.business.model.ItemAvgData;
import com.zhongda.monitor.business.model.WiselyResponse;
import com.zhongda.monitor.business.service.StatisticChartService;
import com.zhongda.monitor.business.service.impl.ItemServiceImpl;
import com.zhongda.monitor.core.service.WebSocketService;
import com.zhongda.monitor.core.utils.HeaderUtils;

/**
 * 
 * @author Chao.hu 2018年2月7日15:42:47
 *
 */
@RestController
@EnableScheduling
@RequestMapping("/Item")
@Api(value = "项目日均值模块", tags = { "项目日均值模块" })
public class ItemController {

	@Autowired
	ItemServiceImpl ItemServiceImpl;

	@Autowired
	StatisticChartService statisticChartService;

	@Autowired
	WebSocketService webSocketService;

	String token = null;
	String myPoJoId = null;

	HashMap<String, String> userMsg = new HashMap<String, String>();

	/*
	 * @GetMapping("/getAllItem")
	 * 
	 * @ApiOperation(value = "项目信息", notes = "项目信息", code = 200, produces =
	 * "application/json" ,httpMethod = "GET") private List<Item>
	 * getAllItem(HttpServletRequest request){ String token =
	 * HeaderUtils.getTokenFromRequest(request); User user =
	 * TokenUtils.getUserFromeToken(token); int userId = user.getUserId();
	 * return ItemServiceImpl.getAllItem(userId);
	 * 
	 * }
	 */
	/**
	 * 
	 * @param poJoId
	 * @param request
	 * @returnList<ItemAvgData> 
	 * 通过请求获得请求用户的 userid 与点击项目列表的对应项目id 通过 userid
	 * 和项目id 使用springboot提供的定时任务注解 和wensocket
	 * 技术点对点的向用户提供实时数据 其中userMsg记录了全部用户，与对应用户发起该请求的项目id
	 * 
	 */
	@GetMapping("/getItemAvgData")
	@ApiOperation(value = "项目信息", notes = "项目各测点平局值和平局变化率/ {支持websocket实时数据  路径:user/userid/msg,在访问websocket的时候要通过/getItemAvgData这个链接传递当前点击的poJoId,其中userid是指当前用户的userId} ", code = 200, produces = "application/json", httpMethod = "GET")
	@ApiImplicitParam(paramType = "query", name = "poJoId", value = "项目ID", required = true, dataType = "String")
	private List<ItemAvgData> getItemAvgData(@RequestParam String poJoId,
			HttpServletRequest request) {
		synchronized (request) {
			token = HeaderUtils.getTokenFromRequest(request);
			User user = TokenUtils.getUserFromeToken(token);
			String userid = String.valueOf(user.getUserId());
			myPoJoId = poJoId;
			userMsg.put(userid, myPoJoId);
		}

		return ItemServiceImpl.selectItemAvgDataByPojoId(poJoId);
	}

	/**
	 * 这是一个定时任务，基于/getItemAvgData这个请求 对全局变量userMsg里的所有数据进行遍历
	 * 向发起了该请求的用户提供指定项目的日均值数据
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedRate = 1000)
	public void callback() throws Exception {		
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
