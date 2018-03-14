package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.business.model.fictitious.MyItem;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.business.service.impl.ItemServiceImpl;
import com.zhongda.monitor.core.model.Result;

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

	@Resource
	private ProjectService projectService;

	public static HashMap<String, String> userMsg = new HashMap<String, String>();

	/**
	 * 
	 * @param poJoId
	 * @param request
	 * @returnList<ItemAvgData> 
	 * 通过请求获得请求用户的 userid 与点击项目列表的对应项目id 通过 userid
	 *                          
     * 和项目id 使用springboot提供的定时任务注解 和wensocket
     * 技术点对点的向用户提供实时数据 其中userMsg记录了全部用户，与对应用户发起该请求的项目id
	 * 
	 */
	@GetMapping("/getWebSocket")
	@ApiOperation(value = "项目信息", notes = "项目各测点平局值和平局变化率 支持websocket实时数据  使用websocket的必须调用接口,该接口会记录当前userId 和pojoId,websocket会读取该值，向userId 实时返回pojoId的数据} ", code = 200, produces = "application/json", httpMethod = "GET")
	@ApiImplicitParam(paramType = "query", name = "poJoId", value = "项目ID", required = true, dataType = "String")
	private Result<String> getItemAvgDataGet(@RequestParam String poJoId) {
		
		synchronized (this.getClass()) {
			
			User user = ShiroUtils.getCurrentUser();
			
			String userid = String.valueOf(user.getUserId());
			
			String myPoJoId = poJoId;
			
			userMsg.put(userid, myPoJoId);
		}
		
		return new Result<String>().success("操作成功", "操作成功，WebSocket链接成功则会向poJoId"+poJoId+"实时提供数据"); 
	}
	/**
	 * 
	 * @param poJoId
	 * @param request
	 * @returnList<ItemAvgData> 
	 * 通过请求获得请求用户的 userid 与点击项目列表的对应项目id 通过 userid
	 *                          
     * 和项目id 使用springboot提供的定时任务注解 和wensocket
     * 技术点对点的向用户提供实时数据 其中userMsg记录了全部用户，与对应用户发起该请求的项目id
	 * 
	 */
	@GetMapping("/getItemAvgData")
	@ApiOperation(value = "项目信息", notes = "项目模块右变的数据，目前数据库数据不全，平局值取得2017—10-30号的数据 ", code = 200, produces = "application/json", httpMethod = "GET")
	@ApiImplicitParam(paramType = "query", name = "poJoId", value = "项目ID", required = true, dataType = "String")
	private Result<MyItem> getItemAvgDataPost(@RequestParam String poJoId) {
		
		return ItemServiceImpl.packagItemLeftData(poJoId); 
	}
}
