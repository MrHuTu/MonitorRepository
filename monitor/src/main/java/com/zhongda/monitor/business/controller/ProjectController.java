package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.utils.TokenUtils;
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
	public Result<List<Project>> loadMap(HttpServletRequest request) {
		String token = HeaderUtils.getTokenFromRequest(request);
		User user = TokenUtils.getUserFromeToken(token);
		return new Result<List<Project>>().success("操作成功", projectService.loadHome(user.getUserId()));
	}

}
