package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.ItemAvgData;

public interface ItemService {
	/**
	 * 取当前用户下的所有项目
	 * @param userId
	 * @return
	 */
	/*List<Item> getAllItem(int userId);*/
	
	/**
	 * 动态传入表明查询数据
	 * 目前来说 不同表明，字段相同的sql能满足需求
	 */
	ItemAvgData selectItemAvgData(String tableName,String poJoId);
	
	/**
	 * 根据项目ID取全部的表名等消息
	 * @param poJoId
	 * @return
	 */
	List<ItemAvgData> selectItemAvgDataByPojoId(String poJoId);
	
}
