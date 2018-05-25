package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.Sensor;
import com.zhongda.monitor.business.model.fictitious.MonitorType;
import com.zhongda.monitor.management.model.PaginationResult;

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
	 * 
	 * @param sensor
	 * @return
	 */
	boolean addSensor(Sensor sensor);

	/**
	 * 查询传感器信息通过项目ID和检测类型
	 * 
	 * @param projectId
	 * @return
	 */
	PaginationResult selectSensorByProjectId(Integer projectId,
			Integer monitorType, int offset, int limit, String condition);

	/**
	 * 查询检测指标类型通过项目Id
	 * 
	 * @param projectId
	 * @return
	 */
	List<Sensor> selectMonitorTypeByPro(Integer projectId);

	/**
	 * 后台管理删除传感器信息
	 * 
	 * @param projectId
	 * @param monitorType
	 * @return
	 */
	boolean deleteSensorInfo(Integer sensorId, Integer monitorType,
			Integer projectId);

	/**
	 * 修改传感器数据
	 * 
	 * @param sensor
	 * @return
	 */
	boolean updateByPrimaryKeySelective(Sensor sensor);
}
