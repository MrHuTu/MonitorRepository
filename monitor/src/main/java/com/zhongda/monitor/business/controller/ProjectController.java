package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.ProjectSelectCondition;
import com.zhongda.monitor.business.model.fictitious.MonitorIndicator;
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
@Api(value = "项目模块", tags = { "项目操作接口" })
public class ProjectController {

	@Resource
	private ProjectService projectService;

	@GetMapping("/home")
	@ApiOperation(value = "首页数据", httpMethod = "GET", response = Result.class, notes = "加载首页的数据")
	public Result<Map<String, Object>> loadMap() {
		User user = ShiroUtils.getCurrentUser();
		System.out.println(user.getUserName());
		return new Result<Map<String, Object>>().success("操作成功",
				projectService.loadHome(user.getUserId()));

	}

	@GetMapping(value = "/queryProjects")
	@ApiOperation(value = "项目数据", httpMethod = "GET", response = Result.class, notes = "查询用户下的所有项目")
	public Result<List<Project>> queryProject() {
		User user = ShiroUtils.getCurrentUser();
		return new Result<List<Project>>().setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(projectService.queryProjectByUserId(user.getUserId()));
	}

	@GetMapping(value = "/queryProMoData/{projectId}")
	@ApiOperation(value = "传感器最近一次数据", httpMethod = "GET", response = Result.class, notes = "查询项目下所有传感器最近一次数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目ID", required = true, dataType = "int", paramType = "path") })
	public Result<List<MonitorIndicator>> queryPromonitor(
			@PathVariable Integer projectId) {
		return new Result<List<MonitorIndicator>>().setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(projectService.queryProMonitor(projectId));
	}
	
	@GetMapping("/getAllProject")
	@ApiOperation(value = "项目列表", httpMethod = "GET", response = Result.class, notes = "加载非admin用户下的所有项目，用户为admin时加载全部项目，对应项目模块的信息")
	private List<Project> getAllProject(){
		User user = ShiroUtils.getCurrentUser();
		int userId = user.getUserId();
		ProjectSelectCondition projectSelectCondition = new ProjectSelectCondition(String.valueOf(userId));
	//	projectSelectCondition.setUserId(userId);
		return projectService.getAllProject(projectSelectCondition);
		
	}
}
