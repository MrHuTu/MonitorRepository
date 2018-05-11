package com.zhongda.monitor.business.service;

import java.util.List;
import java.util.Map;

import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.ProjectSelectCondition;
import com.zhongda.monitor.business.model.StatisticChart;

/**
 * 
 * Title:项目业务接口
 *
 * Description:处理项目的增删改查操作
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年3月5日 下午3:04:55
 */
public interface ProjectService {

	/**
	 * 首页数据处理
	 * 
	 * @param userId
	 * @return
	 */
	/* List<Project> loadHome(Integer userId); */

	/**
	 * 首页数据处理
	 * 
	 * @param userId
	 * @return
	 */
	Map<String, Object> loadHome(Integer userId);

	/**
	 * 查询用户下所有项目
	 * 
	 * @param userId
	 * @return
	 */
	List<Project> queryProjectByUserId(Integer userId);

	/**
	 * 查询项目下的传感器数据
	 * 
	 * @param projectId
	 * @return
	 */
	List<StatisticChart> queryProMonitor(Integer projectId);

	/**
	 * 加载非admin用户下的所有项目，用户为admin时加载全部项目，对应项目模块的信息
	 * 
	 * @param userId
	 * @return
	 * @author huchao 2018年3月26日17:07:27
	 */
	List<Project> getAllProject(ProjectSelectCondition userId);

	/**
	 * 根据projectId 查询项目信息
	 * 
	 * @param projectId
	 * @return
	 */
	Project selectByPrimaryKey(String projectId);

	/**
	 * 调用存储过程查询首页数据
	 * 
	 * @param userId
	 * @return
	 */
	Map<String, Object> selectHomeP(Integer userId);

	/**
	 * 查询所有项目
	 * 
	 * @return
	 */
	List<Project> selectAll();
	
	/**
	 * 根据项目添加项目添加项目
	 * @return
	 */
	int addProject(Project project);
	
	String deleteProjects(String projectIds);
}
