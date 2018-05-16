package com.zhongda.monitor.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.ProjectSelectCondition;

public interface ProjectMapper {
	int deleteByPrimaryKey(Integer projectId);

	int insert(Project record);

	Project selectByPrimaryKey(Integer projectId);

	int updateByPrimaryKeySelective(Project record);

	int updateByPrimaryKey(Project record);

	/**
	 * 插入项目
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(Project record);

	/**
	 * 根据项目id删除多个项目
	 * 
	 * @param projectIds
	 * @return
	 */
	int deleteProjects(String projectIds);

	/**
	 * 项目关联statistic_chart表关联threshold表，查询出项目下的预警值和传感器类型所在的表名
	 * 
	 * @param userId
	 * @return 项目集合
	 */
	List<Project> selectProCharThByUserId(
			@Param(value = "userId") Integer userId);

	/**
	 * 查询用户下所有项目
	 * 
	 * @param userId
	 * @return
	 */
	List<Project> selectProjectByUserId(@Param(value = "userId") Integer userId);

	List<Project> getAllProject(ProjectSelectCondition userId);

	/**
	 * 查询所有项目
	 * 
	 * @return
	 */
	List<Project> selectAll();

}