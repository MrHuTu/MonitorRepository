package com.zhongda.monitor.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.report.service.WordUtil2007Service;
import com.zhongda.monitor.report.utils.Download;

/**
 * 
 * Title: 报表控制器
 *
 * Description:处理报表业务
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年3月21日 上午9:45:43
 */
@RestController()
@RequestMapping(value = "/download", produces = { "application/OCTET-STREAM;charset=UTF-8" })
@Api(value = "下载模块", tags = { "下载操作接口" })
public class ReportsContorllerOfWord {

	
	
	
	@Autowired	
	private WordUtil2007Service wordUtil2007Service;

	
	
	@GetMapping("/downloadWordReport")
	@ApiOperation(value = "报告信息,@author 胡超", notes = "生成当前项目报告",  httpMethod = "GET")	
	private  ResponseEntity<byte[]> generateWord(@RequestParam("pojoId") String pojoId) throws IOException {
		
		 	
			return Download.downloadSolve(wordUtil2007Service.generateWord(pojoId), false);

	}

}
