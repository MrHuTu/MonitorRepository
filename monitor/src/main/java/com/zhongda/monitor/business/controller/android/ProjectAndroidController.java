package com.zhongda.monitor.business.controller.android;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.business.model.fictitious.MonitorType;
import com.zhongda.monitor.business.model.fictitious.PublicSensorData;
import com.zhongda.monitor.business.service.PublicSensorDataService;
import com.zhongda.monitor.business.service.SensorService;
import com.zhongda.monitor.core.model.Result;

/**
 * 
 * Title:
 *
 * Description:
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年5月7日 下午2:54:39
 */
@RestController
@RequestMapping(value = "/androidPro")
@Api(tags = { "安卓项目操作接口" })
public class ProjectAndroidController {

	@Resource
	private SensorService sensorService;

	@Resource
	private PublicSensorDataService publicSensorDataService;

	@GetMapping("/monitorType")
	@ApiOperation(value = "实时界面监测指标--maoping.li", httpMethod = "GET", response = Result.class, notes = "获取实时界面，检测指标数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目ID", required = true, dataType = "int", paramType = "query") })
	public Result<List<MonitorType>> queryMonitorType(Integer projectId) {
		return new Result<List<MonitorType>>().setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(sensorService.selectSensorByPro(projectId));

	}

	@GetMapping(value = "/querySenData")
	@ApiOperation(value = "传感器数据--maoping.li", httpMethod = "GET", response = Result.class, notes = "按时间段查找传感器数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tableName", value = "数据存放表名", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "sensorNumber", value = "传感器编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "smuNumber", value = "采集器编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "smuChannel", value = "采集器通道", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", required = false, dataType = "string", paramType = "query") })
	public Result<List<PublicSensorData>> querySensorData(String tableName,
			String sensorNumber, String smuNumber, String smuChannel,
			String beginTime, String endTime) {
		return new Result<List<PublicSensorData>>()
				.setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(
						publicSensorDataService.querySensorDatas(tableName,
								sensorNumber, smuNumber, smuChannel, beginTime,
								endTime));
	}
}
