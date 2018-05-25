package com.zhongda.monitor.management.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.business.model.Sensor;
import com.zhongda.monitor.business.model.fictitious.PublicSensorData;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.business.service.PublicSensorDataService;
import com.zhongda.monitor.business.service.SensorService;
import com.zhongda.monitor.management.model.PaginationResult;
import com.zhongda.monitor.management.service.ManagerService;

/**
 * 
 * Title:
 *
 * Description:
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年5月17日 下午4:46:17
 */
@Controller
@RequestMapping("/page")
public class PageController {

	@Resource
	private ProjectService projectService;

	@Resource
	private SensorService sensorService;

	@Resource
	private UserService userService;

	@Resource
	private ManagerService managerService;

	@RequestMapping("/queryProject")
	public @ResponseBody PaginationResult queryProject(int offset, int limit,
			String condition) {
		return projectService.selectAllProjectByPage(offset, limit, condition);

	}

	@RequestMapping("/queryUser")
	public @ResponseBody PaginationResult queryUser(int offset, int limit,
			String condition) {
		return userService.selectAllUser(offset, limit, condition);
	}

	@RequestMapping(value = "/queryMoniType")
	public @ResponseBody List<Sensor> queryMoniType(Integer projectId) {
		return sensorService.selectMonitorTypeByPro(projectId);
	}

	@RequestMapping("/querySensor")
	public @ResponseBody PaginationResult querySensor(int offset, int limit,
			String projectId, Integer monitorType, String condition) {
		return sensorService.selectSensorByProjectId(
				Integer.valueOf(projectId), monitorType, offset, limit,
				condition);
	}

	@RequestMapping("/querySensorData")
	public @ResponseBody PaginationResult querySensorData(String sensorData,
			int offset, int limit, String tableName) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		PublicSensorData readValue = objectMapper.readValue(sensorData,
				PublicSensorData.class);
		if (isAllFieldNull(readValue)) {
			return new PaginationResult(0, new ArrayList<PublicSensorData>());
		} else {
			return managerService.querySensorData(offset, limit, tableName,
					readValue.getSensorNumber(), readValue.getBeginTimes(),
					readValue.getEndTimes(), readValue.getSmuNumber(),
					readValue.getSmuChannel());
		}
	}

	@RequestMapping("/querySmuId")
	public @ResponseBody List<PublicSensorData> querySmuId(String tableName) {
		return managerService.querySmuidGroup(tableName);
	}

	public boolean isAllFieldNull(Object obj) throws Exception {
		Class stuCla = (Class) obj.getClass();// 得到类对象
		Field[] fs = stuCla.getDeclaredFields();// 得到属性集合
		boolean flag = true;
		for (Field f : fs) {// 遍历属性
			f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
			Object val = f.get(obj);// 得到此属性的值
			if (val != null && !"".equals(val)) {// 只要有1个属性不为空,那么就不是所有的属性值都为空
				flag = false;
				break;
			}
		}
		return flag;
	}
}
