package com.zhongda.monitor.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.ProjectSelectCondition;

public interface ProjectMapper {
	int deleteByPrimaryKey(Integer projectId);

	int insert(Project record);

	int insertSelective(Project record);

	Project selectByPrimaryKey(Integer projectId);

	int updateByPrimaryKeySelective(Project record);

	int updateByPrimaryKey(Project record);

	/**
	 * 项目关联statistic_chart表关联threshold表，查询出项目下的预警值和传感器类型所在的表名
	 * 
	 * @param userId
	 * @return 项目集合
	 */
	List<Project> selectProCharThByUserId(
			@Param(value = "userId") Integer userId);

	/**
	 * 查询用户下的所有项目及其所有测点
	 * 
	 * @param userId
	 * @return project集合
	 */
	List<Project> selectProSenScByUserId(@Param(value = "userId") Integer userId);

	/**
	 * 查询用户下所有项目
	 * 
	 * @param userId
	 * @return
	 */
	List<Project> selectProjectByUserId(@Param(value = "userId") Integer userId);

	List<Project> getAllProject(ProjectSelectCondition userId);
}