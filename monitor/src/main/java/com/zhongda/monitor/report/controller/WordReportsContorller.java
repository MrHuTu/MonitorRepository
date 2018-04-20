package com.zhongda.monitor.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.report.service.WordUtil2007Service;
import com.zhongda.monitor.report.utils.DownloadUtils;

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
@RequestMapping(value = "/download")
@Api(value = "下载模块", tags = { "下载操作接口" })
public class WordReportsContorller {

	
	
	
	@Autowired	
	private WordUtil2007Service wordUtil2007Service;

	
	
	@GetMapping("/downloadWordReport")
	@ApiOperation(value = "报告信息 --chao.hu", notes = "生成当前项目报告 --目前功能不全",  httpMethod = "GET")	
	@ApiImplicitParams({ @ApiImplicitParam(name = "pojoId", value = "项目Id", required = true, dataType = "String", paramType = "query"),
						@ApiImplicitParam(name = "time", value = "要生成日报的日期(yyyy-MM-dd)", required = true, dataType = "String", paramType = "query")})
	private Object generateWord(@RequestParam("pojoId") String pojoId,@RequestParam("time") String time) throws IOException {
		
			return DownloadUtils.downloadSolve(wordUtil2007Service.generateWord(pojoId,time), false);
				

	}
	
	
	
}
