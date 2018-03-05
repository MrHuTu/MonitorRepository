package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.business.model.ItemAvgData;
import com.zhongda.monitor.business.service.StatisticChartService;
import com.zhongda.monitor.business.service.impl.ItemServiceImpl;
/**
 * 
 * @author Chao.hu 2018年2月7日15:42:47
 *
 */
@RestController
@RequestMapping("/Item")
@Api(value = "项目日均值模块", tags = { "项目日均值模块" })
public class ItemController {
	@Autowired
	ItemServiceImpl ItemServiceImpl;
	@Autowired
	StatisticChartService statisticChartService;
	/*@GetMapping("/getAllItem")
	@ApiOperation(value = "项目信息", notes = "项目信息", code = 200, produces = "application/json" ,httpMethod = "GET")
	private List<Item> getAllItem(HttpServletRequest request){
		String token = HeaderUtils.getTokenFromRequest(request);
		User user = TokenUtils.getUserFromeToken(token);
		int userId = user.getUserId();
		return ItemServiceImpl.getAllItem(userId);
		
	}*/
	@ResponseBody
	@GetMapping("/getItemAvgData")
	@ApiOperation(value = "项目信息", notes = "项目各测点平局值和平局变化率", code = 200, produces = "application/json" ,httpMethod = "GET")
	 @ApiImplicitParam(paramType="query", name = "poJoId", value = "项目ID", required = true, dataType = "String")
	private List<ItemAvgData> getItemAvgData(@RequestParam String poJoId){
			
		return ItemServiceImpl.selectItemAvgDataByPojoId(poJoId);
		
	}
}
