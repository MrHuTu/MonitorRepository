package com.zhongda.monitor.business.service;

import java.util.List;
import java.util.Map;

public interface UserProjectService {

	/**
	 * 查询所有项目下的用户
	 * 
	 * @return
	 */
	Map<Integer, List<String>> selectAllPuser();

	/**
	 * 查询所有用户下的项目
	 * 
	 * @return
	 */
	Map<Integer, List<String>> selectAllUpro();
	/**
	 * 根据项目id，用户id删除项目用户表
	 * @param projectId
	 * @param userId
	 * @return
	 */
	boolean remove(int projectId,int userId);
	/**
	 * 添加项目用户关系到项目用户表
	 * @param projectId
	 * @param userId
	 * @return
	 */
	boolean add(int projectId, int userId);
}
