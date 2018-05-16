package com.zhongda.monitor.management.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.management.model.PaginationResult;

@Controller
@RequestMapping("/page")
public class PageController {

	@Resource
	private ProjectService projectService;

	@RequestMapping("/queryProject")
	public @ResponseBody PaginationResult queryProject(int offset, int limit) {
		System.out
				.println("sdkajfosafsa0fsakflwkldfkl========================");
		return projectService.selectAllProjectByPage(offset, limit);

	}

}
