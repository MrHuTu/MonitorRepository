package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.utils.TokenUtils;
import com.zhongda.monitor.business.model.MonitorType;
import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.core.utils.HeaderUtils;

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
	public Result<Map<String, Object>> loadMap(HttpServletRequest request) {
		String token = HeaderUtils.getTokenFromRequest(request);
		User user = TokenUtils.getUserFromeToken(token);
		return new Result<Map<String, Object>>().success("操作成功", projectService.loadHome(user.getUserId()));

	}

	@GetMapping("/queryProSen")
	@ApiOperation(value = "测点数据", httpMethod = "GET", response = Result.class, notes = "查询项目信息及其所有测点信息")
	public Result<List<Project>> queryProSenItName(HttpServletRequest request) {
		String token = HeaderUtils.getTokenFromRequest(request);
		User user = TokenUtils.getUserFromeToken(token);
		return new Result<List<Project>>()
				.setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(
						projectService.queryProSenItemNameByUserId(user
								.getUserId()));
	}

	@GetMapping(value = "/queryProjects")
	@ApiOperation(value = "项目数据", httpMethod = "GET", response = Result.class, notes = "查询用户下的所有项目")
	public Result<List<Project>> queryProject(HttpServletRequest request) {
		String token = HeaderUtils.getTokenFromRequest(request);
		User user = TokenUtils.getUserFromeToken(token);
		return new Result<List<Project>>().setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(projectService.queryProjectByUserId(user.getUserId()));
	}

	@GetMapping(value = "/queryProMoData/{projectId}")
	@ApiOperation(value = "传感器最近一次数据", httpMethod = "GET", response = Result.class, notes = "查询项目下所有传感器最近一次数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目ID", required = true, dataType = "int", paramType = "path") })
	public Result<List<MonitorType>> queryPromonitor(
			@PathVariable Integer projectId) {
		return new Result<List<MonitorType>>().setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(projectService.queryProMonitor(projectId));
	}
	
	@GetMapping("/getAllProject")
	@ApiOperation(value = "项目列表", httpMethod = "GET", response = Result.class, notes = "加载所有项目")
	private List<Project> getAllProject(HttpServletRequest request){
		String token = HeaderUtils.getTokenFromRequest(request);
		User user = TokenUtils.getUserFromeToken(token);
		int userId = user.getUserId();
		return projectService.getAllProject(userId);
		
	}
}
