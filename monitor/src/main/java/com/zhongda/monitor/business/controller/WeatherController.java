package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.business.service.WeatherService;
import com.zhongda.monitor.core.model.Result;

@RestController
@RequestMapping("/weather")
@Api(tags = { "天气接口" })
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@GetMapping("/{cityName}")
	@ApiOperation(value = "天气信息--chao.hu", notes = "通过城市名获取当前时间天气", response = Result.class, httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(name = "cityName", value = "城市名称", required = true, dataType = "String", paramType = "path") })
	private Result<String> getItemAvgDataPost(@PathVariable String cityName) {
		return new Result<String>().success("查询成功",
				weatherService.getWeather(cityName));
	}
}
