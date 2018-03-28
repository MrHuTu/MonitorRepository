package com.zhongda.monitor.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.business.model.Sensor;

public interface SensorMapper {
	int deleteByPrimaryKey(Integer sensorId);

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
	List<Sensor> selectLastData(String tableName,
			@Param(value = "projectId") Integer projectId,
			@Param(value = "monitorType") Integer monitorType);

	/**
	 * 调用存储过程测试
	 * 
	 * @param tableNames
	 * @param projectIds
	 * @param detectionTypeIds
	 * @param nameCount
	 * @return
	 */
	List<List<Sensor>> selectHomeP(
			@Param(value = "tableNames") String tableNames,
			@Param(value = "projectIds") String projectIds,
			@Param(value = "detectionTypeIds") String detectionTypeIds,
			@Param(value = "nameCount") Integer nameCount);

}