package com.zhongda.monitor.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.business.model.StatisticChart;

public interface StatisticChartMapper {
	int deleteByPrimaryKey(Integer statisticChartId);

	int insert(StatisticChart record);

	int insertSelective(StatisticChart record);

	StatisticChart selectByPrimaryKey(Integer statisticChartId);

	int updateByPrimaryKeySelective(StatisticChart record);

	int updateByPrimaryKey(StatisticChart record);

	/**
	 * 删除数据通过项目Id和检测指标
	 * 
	 * @param projectId
	 * @param monitorType
	 * @return
	 */
	int deleteStatisChaByProAndMT(
			@Param(value = "projectId") Integer projectId,
			@Param(value = "monitorType") Integer monitorType);

	/**
	 * 查询StatisticChart表通过项目ID
	 * 
	 * @param projectId
	 * @return
	 */
	List<StatisticChart> selectStatisCharByProjectId(Integer projectId);

	List<StatisticChart> selectByPojoId(int poJoId);

	/**
	 * 调用存储过程查询传感器数据
	 * 
	 * @param projectId
	 * @return
	 */
	List<List<StatisticChart>> selectLastData(
			@Param(value = "projectId") Integer projectId);

	/**
	 * 调用存储过程查询传感器数据
	 * 
	 * @param projectId
	 * @return
	 */
	List<List<StatisticChart>> selectAndroidSensorData(
			@Param(value = "projectId") Integer projectId);

	/**
	 * 根据项目ID和检测指标查询个数
	 * 
	 * @param projectId
	 * @param detectionTypeId
	 * @return
	 */
	int selectCountByproAndMT(@Param(value = "projectId") Integer projectId,
			@Param(value = "detectionTypeId") Integer detectionTypeId);

}