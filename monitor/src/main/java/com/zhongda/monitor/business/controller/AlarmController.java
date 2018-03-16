package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.security.RoleSign;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.business.model.Alarm;
import com.zhongda.monitor.business.service.AlarmService;
import com.zhongda.monitor.core.model.Result;

@RestController
@RequestMapping("/alarm")
@Api(tags = { "告警信息操作接口" })
public class AlarmController {

	@Resource
	AlarmService alarmService;

	@GetMapping("/queryAlarm")
	@ApiOperation(value = "分页按条件查询当前用户下的告警信息", httpMethod = "GET", response = Result.class, notes = "分页查询当前用户下的告警信息")
	public Result<Map<String, Object>> selectAlarmByUserId(Alarm alarm) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.hasRole(RoleSign.ADMIN)
				&& !subject.hasRole(RoleSign.SUPER_ADMIN)) {
			// 非管理员用户，只可查看自己的告警信息，查询条件增加userId
			User user = ShiroUtils.getCurrentUser();
			alarm.setUserId(user.getUserId());
		}
		Map<String, Object> map = alarmService.selectPageAlarmByQuery(alarm);
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		return result.success("查询成功", map);
	}

	@PutMapping("/{alarmId}")
	@ApiOperation(value = "修改告警信息状态", httpMethod = "PUT", response = Result.class, notes = "修改告警信息状态")
	@ApiImplicitParams({ @ApiImplicitParam(name = "alarmId", value = "告警id", required = true, dataType = "int", paramType = "path") })
	public Result<Alarm> selectAlarmByUserId(@PathVariable Integer alarmId) {
		return alarmService.updateAlarmStatusByAlarmId(alarmId);
	}

	@DeleteMapping("/deleteAlarm")
	@ApiOperation(value = "删除告警信息   暂时没用上", httpMethod = "DELETE", response = Result.class, notes = "删除告警信息  暂时没用上")
	public Result<String> deleteAlarm(Alarm alarm) {// 根据条件删除告警信息
		Subject subject = SecurityUtils.getSubject();
		if (!subject.hasRole(RoleSign.ADMIN)
				&& !subject.hasRole(RoleSign.SUPER_ADMIN)) {
			// 非管理员用户，只可查看自己的告警信息，查询条件增加userId
			User user = ShiroUtils.getCurrentUser();
			alarm.setUserId(user.getUserId());
		}

		return alarmService.deleteAlarm(alarm, 100);
	}

	@GetMapping("/queryAlarmCount")
	@ApiOperation(value = "统计当前用户下的所有未确认的告警条数", httpMethod = "GET", response = Result.class, notes = "统计当前用户下的所有未确认的告警条数")
	public Result<Integer> selectAlarmCount() {
		User user = new User();
		Subject subject = SecurityUtils.getSubject();
		if (!subject.hasRole(RoleSign.ADMIN)
				&& !subject.hasRole(RoleSign.SUPER_ADMIN)) {
			// 非管理员用户，只可查看自己的告警信息，查询条件增加userId
			user = ShiroUtils.getCurrentUser();
		}
		return alarmService.alarmCount(user.getUserId());
	}

}
