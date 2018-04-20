package com.zhongda.monitor.business.service;

import java.util.Map;

public interface ThresholdService {

	/**
	 * 查询阈值通过项目ID
	 * 
	 * @param projectId
	 * @return
	 */
	Map<String, Map<String, Object>> selectThresholdDataByProId(
			Integer projectId);

}
