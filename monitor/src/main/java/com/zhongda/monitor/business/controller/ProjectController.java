package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.business.mapper.SensorMapper;
import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.ProjectSelectCondition;
import com.zhongda.monitor.business.model.StatisticChart;
import com.zhongda.monitor.business.service.AlarmService;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.core.model.Result;

/**
 * 
 * <p>
 * 项目控制器
 * </p>
 * 
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @sine 2018年2月7日
 */

@RestController()
@RequestMapping(value = "/project")
@Api(tags = { "项目操作接口" })
public class ProjectController {

	@Resource
	private ProjectService projectService;

	@Resource
	private SensorMapper sensorMapper;

	@Resource
	private AlarmService alarmService;

	@GetMapping("/home.gzip")
	@ApiOperation(value = "首页数据--maoping.li", httpMethod = "GET", response = Result.class, notes = "加载首页的数据")
	public Result<Map<String, Object>> loadMap(HttpServletResponse response) {
		User user = ShiroUtils.getCurrentUser();
		System.out.println(user.getUserName());
		// response.setHeader("Access-Control-Allow-Origin", "*");
		return new Result<Map<String, Object>>().success("操作成功",
				projectService.loadHome(user.getUserId()));

	}

	@GetMapping("/homep.gzip")
	@ApiOperation(value = "首页数据--maoping.li", httpMethod = "GET", response = Result.class, notes = "调用存储过程加载首页的数据")
	public Result<Map<String, Object>> loadhomep(HttpServletResponse response) {
		User user = ShiroUtils.getCurrentUser();
		// response.setHeader("Access-Control-Allow-Origin", "*");
		return new Result<Map<String, Object>>().success("操作成功",
				projectService.loadHome(user.getUserId()));

	}

	@GetMapping(value = "/queryProjects")
	@ApiOperation(value = "项目数据--maoping.li", httpMethod = "GET", response = Result.class, notes = "查询用户下的所有项目")
	public Result<List<Project>> queryProject() {
		User user = ShiroUtils.getCurrentUser();
		return new Result<List<Project>>().setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(projectService.queryProjectByUserId(user.getUserId()));
	}

	@GetMapping(value = "/queryProMoData/{projectId}")
	@ApiOperation(value = "传感器最近一次数据--maoping.li", httpMethod = "GET", response = Result.class, notes = "查询项目下所有传感器最近一次数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目ID", required = true, dataType = "int", paramType = "path") })
	public Result<List<StatisticChart>> queryPromonitor(
			@PathVariable Integer projectId) {
		return new Result<List<StatisticChart>>().setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(projectService.queryProMonitor(projectId));
	}

	@GetMapping("/getAllProject")
	@ApiOperation(value = "项目列表 --chao.hu", httpMethod = "GET", response = Result.class, notes = "加载非admin用户下的所有项目，用户为admin时加载全部项目，对应项目模块的信息")
	private Result<List<Project>> getAllProject() {
		User user = ShiroUtils.getCurrentUser();

		int userId = user.getUserId();

		ProjectSelectCondition projectSelectCondition = new ProjectSelectCondition(
				String.valueOf(userId));

		return new Result<List<Project>>().setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(projectService.getAllProject(projectSelectCondition));

	}
	
	@RequestMapping("/addProject")
	@ResponseBody
	private void addProject(@RequestBody Project project){
		projectService.addProject(project);
	}

	// @GetMapping("/test")
	// @ApiOperation(value = "测试", httpMethod = "GET", response = Result.class,
	// notes = "测试")
	// public Result<Map<Object, Object>> Tests(HttpServletResponse response) {
	// MapParam param = new MapParam("id", "pname",
	// MapParam.ValueClass.STRING.getCode());
	// return new Result<Map<Object, Object>>().success("操作成功",
	// projectService.queryTest(param));
	//
	// }

	// @GetMapping("/querTest")
	// @ApiOperation(value = "项目列表", httpMethod = "GET", response =
	// Result.class, notes = "测试")
	// private Result<List<List<Sensor>>> querTest(HttpServletResponse response)
	// {
	// response.setHeader("Access-Control-Allow-Origin", "*");
	// return new Result<List<List<Sensor>>>()
	// .setCode(Result.SUCCESS)
	// .setMsg("操作成功")
	// .setData(
	// sensorMapper
	// .selectHomeP(
	// "static_level_data,pull_line_displacement_data,static_level_data,pull_line_displacement_data,static_level_data,pull_line_displacement_data,static_level_data,pull_line_displacement_data",
	// "261,261,262,262,263,263,264,264",
	// "16,17,16,17,16,17,16,17", 8));
	//
	// }

}
