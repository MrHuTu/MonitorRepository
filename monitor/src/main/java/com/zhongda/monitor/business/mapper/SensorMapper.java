package com.zhongda.monitor.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.business.model.Sensor;

public interface SensorMapper {
	int deleteByPrimaryKey(Integer sensorId);
	
	/**
	 * 添加传感器
	 * @param record
	 * @return
	 */
	int insert(Sensor record);

	int insertSelective(Sensor record);

	Sensor selectByPrimaryKey(Integer sensorId);

	int updateByPrimaryKeySelective(Sensor record);

	int updateByPrimaryKey(Sensor record);

	/**
	 * 查询传感器最后一条数据根据项目ID和检测指标
	 * 
	 * @param projectId
	 * @param monitorType
	 * @return
	 */
	List<Sensor> selectSensorDataByProIdAndMoniType(String tableName,
			@Param(value = "projectId") Integer projectId,
			@Param(value = "monitorType") Integer monitorType);

	/**
	 * 查询传感器最新的一条数据
	 * 
	 * @param tableName
	 * @param projectId
	 * @param monitorType
	 * @return
	 */
	List<List<Sensor>> selectLastData(
			@Param(value = "projectId") Integer projectId);

	/**
	 * 调用存储过程查询多个表传感器数据
	 * 
	 * @param tableNames
	 * @param projectIds
	 * @param detectionTypeIds
	 * @param nameCount
	 * @return
	 */
	List<List<Sensor>> selectHomeP(@Param(value = "userId") Integer userId);

	/**
	 * 查询传感器信息，通过项目ID
	 * 
	 * @param projectId
	 * @return
	 */
	List<Sensor> selectSensorByPro(@Param(value = "projectId") Integer projectId);

}