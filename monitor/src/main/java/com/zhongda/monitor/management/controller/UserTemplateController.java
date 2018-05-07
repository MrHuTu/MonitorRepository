package com.zhongda.monitor.management.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.business.service.UserProjectService;

@Controller
@RequestMapping(value = "/manage")
public class UserTemplateController {

	@Resource
	private ProjectService projectService;

	@Resource
	private UserService userService;

	@Resource
	private UserProjectService userProjectService;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/landing")
	public String Landing(String userName, String password, Model model)
			throws JsonProcessingException {
		if ("admin".equals("admin") && "zdznyjy@".equals("zdznyjy@")) {
			model.addAttribute("projectList", projectService.selectAll());
			model.addAttribute("userList", userService.selectAll());
			model.addAttribute("pUser", userProjectService.selectAllPuser());
			model.addAttribute("uPro", userProjectService.selectAllUpro());
			return "index";
		} else {
			model.addAttribute("result", "账号或者密码错误");
			return "login";
		}
	}

}
