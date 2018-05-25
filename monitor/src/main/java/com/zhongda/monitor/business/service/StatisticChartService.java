package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.StatisticChart;

public interface StatisticChartService {

	List<StatisticChart> selectByPojoId(int poJoId);

	/**
	 * 根据项目ID和检测指标查询个数
	 * 
	 * @param projectId
	 * @param detectionTypeId
	 * @return
	 */
	int selectCountByproAndMT(Integer projectId, Integer detectionTypeId);

	/**
	 * 删除数据通过项目Id和检测指标
	 * 
	 * @param projectId
	 * @param monitorType
	 * @return
	 */
	int deleteStatisChaByProAndMT(Integer projectId, Integer monitorType);

}
