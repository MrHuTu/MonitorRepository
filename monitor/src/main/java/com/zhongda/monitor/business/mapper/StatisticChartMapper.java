package com.zhongda.monitor.business.mapper;

import java.util.List;

import com.zhongda.monitor.business.model.StatisticChart;

public interface StatisticChartMapper {
	int deleteByPrimaryKey(Integer statisticChartId);

	int insert(StatisticChart record);

	int insertSelective(StatisticChart record);

	StatisticChart selectByPrimaryKey(Integer statisticChartId);

	int updateByPrimaryKeySelective(StatisticChart record);

	int updateByPrimaryKey(StatisticChart record);

	/**
	 * 查询StatisticChart表通过项目ID
	 * 
	 * @param projectId
	 * @return
	 */
	List<StatisticChart> selectStatisCharByProjectId(Integer projectId);

    List<StatisticChart> selectByPojoId(int poJoId);

}