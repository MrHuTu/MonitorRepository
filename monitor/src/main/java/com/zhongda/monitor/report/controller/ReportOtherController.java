package com.zhongda.monitor.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.report.service.ReportPicService;

@RestController()
@RequestMapping(value = "/report")
@Api(value = "", tags = { "项目下已有图片" })
public class ReportOtherController {
	
	@Autowired
	ReportPicService reportPicService;
	
	@GetMapping("/pic")
	@ApiOperation(value = "图片信息 --chao.hu", notes = "项目下已有图片",  httpMethod = "GET")	
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "项目Id", required = true, dataType = "String", paramType = "query")})
	public Result<List<String>>  selectPicById(@RequestParam("id") String id){
		
		
		return reportPicService.selectPicById(id);
		
	}

}
