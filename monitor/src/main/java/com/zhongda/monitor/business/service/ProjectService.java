package com.zhongda.monitor.business.service;

import java.util.List;
import java.util.Map;

import com.zhongda.monitor.business.model.MonitorType;
import com.zhongda.monitor.business.model.Project;

public interface ProjectService {

	/**
	 * 首页数据处理
	 * 
	 * @param userId
	 * @return
	 */
	/*List<Project> loadHome(Integer userId);*/
	
	/**
	 * 首页数据处理
	 * 
	 * @param userId
	 * @return
	 */
	Map<String, Object> loadHome(Integer userId);

	/**
	 * 查询该用户下的所有项目及其传感器
	 * 
	 * @param userId
	 * @return
	 */
	List<Project> queryProSenItemNameByUserId(Integer userId);

	/**
	 * 查询用户下所有项目
	 * 
	 * @param userId
	 * @return
	 */
	List<Project> queryProjectByUserId(Integer userId);

	/**
	 * 查询项目下的传感器数据
	 * 
	 * @param projectId
	 * @return
	 */
	List<MonitorType> queryProMonitor(Integer projectId);

}
