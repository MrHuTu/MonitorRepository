package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.ItemAvgData;
import com.zhongda.monitor.business.model.MyItem;
import com.zhongda.monitor.core.model.Result;

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
	 * 根据项目ID取全部的日均值
	 * 是最终返回给前台的数据
	 * @param poJoId
	 * @returnList<ItemAvgData>
	 */
	List<ItemAvgData> selectItemAvgDataByPojoId(String poJoId);
	
	
	/**
	 * 封装项目模块 实时模块右侧信息
	 * @param poJoId
	 * @return
	 */
	 Result<MyItem> packagItemLeftData(String poJoId);
	
}
