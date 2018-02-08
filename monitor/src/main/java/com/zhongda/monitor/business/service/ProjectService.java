package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.Project;

public interface ProjectService {

	/**
	 * 首页数据处理
	 * 
	 * @param userId
	 * @return
	 */
	List<Project> loadHome(Integer userId);

}
