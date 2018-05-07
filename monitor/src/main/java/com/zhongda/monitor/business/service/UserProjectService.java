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

}
