package com.zhongda.monitor.business.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.business.service.PublicSensorDataService;
import com.zhongda.monitor.business.service.WordUtil2007Service;
import com.zhongda.monitor.business.utils.Download;
import com.zhongda.monitor.core.model.Result;

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
public class ReportsContorller {

	@Resource
	private PublicSensorDataService publicSensorDataService;
	
	@Autowired	
	private WordUtil2007Service wordUtil2007Service;

	@GetMapping(value = "/downloadSenData")
	@ApiOperation(value = "下载传感器数据", httpMethod = "GET", response = Result.class, notes = "下载按时间段查找出来的传感器数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tableName", value = "数据存放表名", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "sensorNumber", value = "传感器编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "smuNumber", value = "采集器编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "smuChannel", value = "采集器通道", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "beginDate", value = "开始时间", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "monitorPoint", value = "测点名称", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "projectName", value = "项目名称", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "monitorTypeName", value = "检测指标名称", required = true, dataType = "string", paramType = "query") })
	public void querySensorData(String tableName, String sensorNumber,
			String smuNumber, String smuChannel, HttpServletResponse response,
			String beginDate, String endDate, String monitorPoint,
			String projectName, String monitorTypeName,
			HttpServletRequest request) {
		publicSensorDataService.querySensorDataList(tableName, sensorNumber,
				smuNumber, smuChannel, response, beginDate, endDate,
				monitorPoint, projectName, monitorTypeName);

	}
	
	@GetMapping("/downloadWord")
	@ApiOperation(value = "报告信息", notes = "生成当前项目报告",  httpMethod = "GET")	
	private  ResponseEntity<byte[]> generateWord(@RequestParam("pojoId") String pojoId) throws IOException {
		
		 	
			return Download.downloadSolve(wordUtil2007Service.generateWord(pojoId), true);

	}

}
