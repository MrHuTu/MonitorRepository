package com.zhongda.monitor.business.service;

import java.util.List;
import java.util.Map;

import com.zhongda.monitor.business.model.Sensor;
import com.zhongda.monitor.business.model.fictitious.MonitorType;

public interface SensorService {

	/**
	 * 查询传感器信息，通过项目ID
	 * 
	 * @param projectId
	 * @return
	 */
	List<MonitorType> selectSensorByPro(Integer projectId);
	
	/**
	 * 添加传感器
	 * @param sensor
	 * @return
	 */
	boolean addSensor(Sensor sensor);
}
