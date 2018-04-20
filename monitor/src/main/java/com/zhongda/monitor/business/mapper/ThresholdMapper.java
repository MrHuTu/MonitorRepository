package com.zhongda.monitor.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.business.model.Threshold;

public interface ThresholdMapper {
	int deleteByPrimaryKey(Integer thresholdId);

	int insert(Threshold record);

	int insertSelective(Threshold record);

	Threshold selectByPrimaryKey(Integer thresholdId);

	int updateByPrimaryKeySelective(Threshold record);

	int updateByPrimaryKey(Threshold record);

	/**
	 * 查询阈值通过项目ID
	 * 
	 * @param projectId
	 * @return
	 */
	List<Threshold> selectThresholdDataByProId(
			@Param(value = "projectId") Integer projectId);
}