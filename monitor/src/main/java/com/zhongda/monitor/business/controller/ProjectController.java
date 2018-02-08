package com.zhongda.monitor.business.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.core.model.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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

	@RequestMapping(value = "/home/{userId}", method = RequestMethod.GET)
	@ApiOperation(value = "首页数据", httpMethod = "GET", response = Result.class, notes = "加载首页的数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path") })
	public Result<List<Project>> loadMap(@PathVariable Integer userId) {
		System.out.println("userId:" + userId);
		return new Result<List<Project>>().setCode(Result.SUCCESS)
				.setMsg("操作成功").setData(projectService.loadHome(userId));
	}

}
