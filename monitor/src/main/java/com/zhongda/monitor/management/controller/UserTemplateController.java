package com.zhongda.monitor.management.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.Sensor;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.business.service.SensorService;
import com.zhongda.monitor.business.service.UserProjectService;
import com.zhongda.monitor.core.service.SysCodeService;

@Controller
@RequestMapping(value = "/manage")
public class UserTemplateController {

	@Resource
	private SensorService sensorService;

	@Resource
	private ProjectService projectService;

	@Resource
	private UserService userService;

	@Resource
	private UserProjectService userProjectService;

	@Resource
	private SysCodeService sysCodeService;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/landing")
	public String Landing(String userName, String password, Model model)
			throws JsonProcessingException {
		if ("admin".equals("admin") && "zdznyjy@".equals("zdznyjy@")) {
			model.addAttribute("userList", userService.selectAll());
			model.addAttribute("proType", sysCodeService.selecttypeCode());
			model.addAttribute("proStatus", sysCodeService.selectProStatus());
			model.addAttribute("pUser", userProjectService.selectAllPuser());
			model.addAttribute("uPro", userProjectService.selectAllUpro());
			return "index";
		} else {
			model.addAttribute("result", "账号或者密码错误");
			return "login";
		}
	}

	@ResponseBody
	@RequestMapping("/addProject")
	public Project addProject(@RequestBody Project project) {
		if (projectService.addProject(project) > 0) {
			return project;
		} else {
			System.out.println("--------");
			return project;
		}
	}

	@ResponseBody
	@RequestMapping("/addUser")
	public User addUser(@RequestBody User user) {
		if (userService.addUser(user)) {
			return user;
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("/addSensor")
	public Sensor addSensor(@RequestBody Sensor sensor) {
		if (sensorService.addSensor(sensor)) {
			return sensor;
		} else {
			return null;
		}
	}

	@RequestMapping("/deleteProjects")
	public void deleteProjects(String projectId, HttpServletResponse response)
			throws IOException {
		// if (projectService.deleteProjects(projectId) > 0) {
		// response.getWriter().print(true);
		// } else {
		// response.getWriter().print(false);
		// }
		System.out.println(projectId);
	}

	@ResponseBody
	@RequestMapping("/deleteUsers")
	public String deleteUsers(@RequestParam("idsForDelete") String ids) {
		// 先不完成删除，以免删除项目
		System.out.println(ids);
		return null;
	}
}
