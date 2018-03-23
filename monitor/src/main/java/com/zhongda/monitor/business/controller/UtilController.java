package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.business.model.fictitious.Weather;
import com.zhongda.monitor.business.service.WeatherService;
import com.zhongda.monitor.core.model.Result;

@RestController
@EnableScheduling
@RequestMapping("/Util")
@Api(value = "通用功能", tags = { "天气" })
public class UtilController {
	
	@Autowired	
	WeatherService weatherService;
	
	@GetMapping("/Weather")
	@ApiOperation(value = "项目信息", notes = "项目模块右变的数据，目前数据库数据不全，平局值取得2017—10-30号的数据 ", code = 200, produces = "application/json", httpMethod = "GET")	
	@ApiImplicitParam (paramType = "query", name = "CityName", value = "城市名称", required = true, dataType = "String")
	private Result<List<Weather>> getItemAvgDataPost(@RequestParam String CityName) {//String CityName,int num
		
		return new Result<List<Weather>>().success("查询成功", weatherService.getWeather(CityName)); 
	}
}
