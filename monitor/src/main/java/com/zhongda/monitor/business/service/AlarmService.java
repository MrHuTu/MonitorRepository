package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.Alarm;
import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.core.model.Result;

/**
 * Title : 告警 业务 接口
 * Description : 处理告警的增删改查操作
 * @Author dengzm
 * @Date 2018年1月26日 下午3:43:38
 */
public interface AlarmService {
	
	/**
	 * 查询出距离现在某段时间内发生的所有告警信息包括对应的告警联系人的信息
	 * @return
	 */
	List<Alarm> selectAlarmAndLinkmanPeriod();
	
	/**
	 * 分页查询出当前用户下未确认的告警信息
	 * @param userId 用户id
	 * @param pageNum 当前页
	 * @param pageSize 每页记录数
	 * @return
	 */
	List<Alarm> selectPageAlarmByUserIdAndNotConfirm(Integer userId, Integer pageNum, Integer pageSize);
	
	/**
	 * 根据查询条件分页查询出当前用户下的告警信息
	 * @param alarm 封装了查询条件的alarm对象(包含当前页和每页记录数)
	 * @return
	 */
	List<Alarm> selectPageAlarmByQuery(Alarm alarm);
	
	/**
	 * 查当前用户下所有告警信息
	 * @param projectId
	 * @return
	 */
	List<Alarm> selectAlarmByProjectId(Integer projectId);
	
	/**
	 * 根据查询条件查询出对应项目下告警信息
	 * 
	 * @param project
	 *            封装了查询条件的对象
	 * @return
	 */
	List<Alarm> selectAlarmWithAlarmCount(Project project);
	
	/**
	 * 修改对应的告警消息状态为确认状态
	 * @param alarmId 告警消息Id
	 * @return
	 */
	Result<Alarm> updateAlarmStatus(Integer alarmId);
	
	/**
	 * 批量修改对应的告警消息状态为确认状态
	 * @param alarmIds 告警消息id串
	 * @return
	 */
	Result<Alarm> updateBatchAlarmStatus(String alarmIds);
	
	/**
	 * 批量修改通过查询条件查询出来的告警消息状态为确认状态
	 * @param alarm 封装的查询条件
	 * @return
	 */
	Result<Alarm> updateBatchAlarmStatusByQuery(Alarm alarm);
}
