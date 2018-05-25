package com.zhongda.monitor.management.service;

import java.util.List;

import com.zhongda.monitor.business.model.fictitious.PublicSensorData;
import com.zhongda.monitor.management.model.PaginationResult;

public interface ManagerService {

	/**
	 * 查询传感器数据
	 * 
	 * @return
	 */
	PaginationResult querySensorData(int offset, int limit, String tableName,
			String sensorNumber, String beginTimes, String endTimes,
			String smuNumber, String smuChannel);

	/**
	 * 查询数据库有的采集器通道
	 * 
	 * @param tableName
	 * @return
	 */
	List<PublicSensorData> querySmuidGroup(String tableName);

}
