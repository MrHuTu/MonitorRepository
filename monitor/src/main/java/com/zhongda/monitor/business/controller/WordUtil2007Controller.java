package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.business.service.WordUtil2007Service;
import com.zhongda.monitor.core.model.Result;

@RestController
@RequestMapping("/wordUtil2007")
@Api(tags = { "报告生成接口" })
public class WordUtil2007Controller {
	@Autowired	
	private WordUtil2007Service WordUtil2007Service;
	
	
	@GetMapping("/getWord/{pojoId}")
	@ApiOperation(value = "报告信息", notes = "生成当前项目报告", response = Result.class, httpMethod = "GET")	
	@ApiImplicitParam(name = "pojoId", value = "项目ID", required = true, dataType = "String", paramType = "path") 
	private Result<String> generateWord(@PathVariable String pojoId) {
	
				 String path = WordUtil2007Service.generateWord(pojoId);
				
			  
			return new Result<String>().success("下载成功",path); 
	}

	
}
