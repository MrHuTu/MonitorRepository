package com.zhongda.monitor.business.service;

import java.util.List;
import java.util.Map;

import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.ProjectSelectCondition;
import com.zhongda.monitor.business.model.fictitious.MonitorIndicator;

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
	List<MonitorIndicator> queryProMonitor(Integer projectId);

	List<Project> getAllProject(ProjectSelectCondition userId);

}
