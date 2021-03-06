package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.business.service.PublicSensorDataService;
import com.zhongda.monitor.core.model.Result;

/**
 * 
 * Title: 传感器模块
 *
 * Description:处理传感器数据业务
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年3月21日 上午9:48:20
 */
@RestController
@RequestMapping(value = "/sensor")
@Api(tags = { "传感器操作接口" })
public class SensorController {

	@Resource
	private PublicSensorDataService publicSensorDataService;

	@GetMapping(value = "/querySenData.gzip")
	@ApiOperation(value = "传感器数据--maoping.li", httpMethod = "GET", response = Result.class, notes = "按时间段查找传感器数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tableName", value = "数据存放表名", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "sensorNumber", value = "传感器编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "smuNumber", value = "采集器编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "smuChannel", value = "采集器通道", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "date", value = "查询时间", required = false, dataType = "string", paramType = "query") })
	public Result<Map<Object, Object>> querySensorData(String tableName,
			String sensorNumber, String smuNumber, String smuChannel,
			String date) {
		return new Result<Map<Object, Object>>()
				.setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(
						publicSensorDataService.querySensorData(tableName,
								sensorNumber, smuNumber, smuChannel, date));
	}

	@GetMapping(value = "/benchmark.gzip")
	@ApiOperation(value = "处理后的传感器数据--maoping.li", httpMethod = "GET", response = Result.class, notes = "通过基准点处理后的传感器数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tableName", value = "数据存放表名", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "sensorNumber", value = "传感器编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "smuNumber", value = "采集器编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "smuChannel", value = "采集器通道", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "sensorNumberBM", value = "基准点传感器编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "smuNumberBM", value = "基准点采集器编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "smuChannelBM", value = "基准点采集器通道", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "date", value = "查询时间", required = false, dataType = "string", paramType = "query") })
	public Result<Map<Object, Object>> Benchmark(String tableName,
			String sensorNumber, String smuNumber, String smuChannel,
			String sensorNumberBM, String smuNumberBM, String smuChannelBM,
			String date) {
		return new Result<Map<Object, Object>>()
				.setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(
						publicSensorDataService
								.selectSenDataforBenchmark(tableName,
										sensorNumber, smuNumber, smuChannel,
										sensorNumberBM, smuNumberBM,
										smuChannelBM, date));

	}

}
