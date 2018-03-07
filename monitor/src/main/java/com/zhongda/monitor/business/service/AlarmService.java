package com.zhongda.monitor.business.service;

import java.util.Map;

import com.zhongda.monitor.business.model.Alarm;
import com.zhongda.monitor.core.model.Result;

/**
 * Title : 告警 业务 接口
 * Description : 处理告警的增删改查操作
 * @Author dengzm
 * @Date 2018年1月26日 下午3:43:38
 */
public interface AlarmService {
	
	/**
	 * 根据查询条件分页查询出当前用户下的告警信息
	 * @param userId 用户id
	 * @return  List<Alarm>
	 */
	Map<String , Object> selectPageAlarmByQuery(Alarm alarm);
	
	/**
	 *根据id修改alarm状态信息
	 * @param alarmId 告警id
	 * 
	 */
	Result<Alarm> updateAlarmStatusByAlarmId(Integer alarmId);
	
	 
	/**
	 * 根据条件删除alarm信息
	 */
	Result<String> deleteAlarm(Alarm alarm);
	/**
	  * 统计当前用户下未确认的告警信息总条数
	  */
	Result<Integer> alarmCount(Integer userId);
}
