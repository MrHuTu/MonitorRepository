package com.zhongda.monitor.management.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.Sensor;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.business.service.PublicSensorDataService;
import com.zhongda.monitor.business.service.SensorService;
import com.zhongda.monitor.business.service.UserProjectService;
import com.zhongda.monitor.core.config.JpushConfig;
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

	@Resource
	private PublicSensorDataService publicSensorDataService;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/zhongjie")
	public String zhongjie() {
		return "zhongjie";
	}

	@RequestMapping(value = "/fuckme")
	@ResponseBody
	public void fuck() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("msg", "麦迪.zhongjie");
		JpushConfig.jpushAndroid(hashMap);
	}

	@RequestMapping("/landing")
	public String Landing(String userName, String password, Model model)
			throws JsonProcessingException {
		if ("admin".equals("admin") && "zdznyjy@".equals("zdznyjy@")) {
			model.addAttribute("userList", userService.selectAll());
			model.addAttribute("proType", sysCodeService.selecttypeCode());
			model.addAttribute("proStatus", sysCodeService.selectProStatus());
			model.addAttribute("vdt", sysCodeService.selectViewDataType());
			model.addAttribute("monitorType",
					sysCodeService.selectMoniTyTableName());
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
			return null;
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
		if (projectService.deleteProjects(projectId) > 0) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
		System.out.println(projectId);
	}

	@RequestMapping("/deleteSensor")
	public void deleteSensor(Integer sensorId, Integer monitorType,
			Integer projectId, HttpServletResponse response) throws IOException {
		if (sensorService.deleteSensorInfo(sensorId, monitorType, projectId)) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}

	@RequestMapping("/deleteUsers")
	public void deleteUsers(Integer userId, HttpServletResponse response)
			throws IOException {
		System.out.println(userId);
		if (userService.deleteUsers(userId)) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}

	@RequestMapping("/updateProject")
	public void updateProject(Project project, HttpServletResponse response)
			throws IOException {
		if (projectService.updateProjectManeger(project) > 0) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}

	@RequestMapping("/updateUser")
	public void uodateUser(User user, HttpServletResponse response)
			throws IOException {
		User userDB = userService.selectByPrimaryKey(user.getUserId());
		if (!userDB.getPassword().equals(user.getPassword())) {
			String password = ShiroUtils.encryptPassword(user.getPassword(),
					user.getUserName());
			user.setPassword(password);
		}
		if (userService.updateUser(user)) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}

	@RequestMapping("/updateSensor")
	public void updateSensor(Sensor sensor, HttpServletResponse response)
			throws IOException {
		if (sensorService.updateByPrimaryKeySelective(sensor)) {

			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}

	@RequestMapping("/updateFirstData")
	public void updateFirstData(String tableName, String sensorNumber,
			String smuNumber, String beginTimes, String endTimes,
			HttpServletResponse response) throws IOException {
		System.out.println(tableName + ":" + sensorNumber + ":" + smuNumber
				+ ":" + beginTimes + ":" + endTimes);
		boolean isOK = publicSensorDataService.updatefirstData(tableName,
				smuNumber, beginTimes, endTimes, sensorNumber);
		if (isOK) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:SS");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@ResponseBody
	@RequestMapping("/delPerInProject")
	public void delPerInProject(int userId, int projectId,
			HttpServletResponse resp) throws IOException {
		resp.getWriter().print(userProjectService.remove(projectId, userId));
	}

	@ResponseBody
	@RequestMapping("/addUserInProject")
	public void addUserInProject(int userId, int projectId,
			HttpServletResponse resp) throws IOException {
		resp.getWriter().print(userProjectService.add(projectId, userId));
	}

	@ResponseBody
	@RequestMapping("/addProjectInUser")
	public void addProjectInUser(int projectId, int userId,
			HttpServletResponse resp) throws IOException {
		resp.getWriter().print(userProjectService.add(projectId, userId));
	}

	@ResponseBody
	@RequestMapping("/delProjectInUser")
	public void delProjectInUser(int projectId, int userId,
			HttpServletResponse resp) throws IOException {
		resp.getWriter().print(userProjectService.remove(projectId, userId));
	}
}
