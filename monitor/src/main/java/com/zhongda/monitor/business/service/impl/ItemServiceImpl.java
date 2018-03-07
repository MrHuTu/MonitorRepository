package com.zhongda.monitor.business.service.impl;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.ItemMapper;
import com.zhongda.monitor.business.model.ItemAvgData;
import com.zhongda.monitor.business.model.StatisticChart;
import com.zhongda.monitor.business.service.ItemService;
import com.zhongda.monitor.business.service.StatisticChartService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemMapper itemMapper;
	@Autowired
	StatisticChartService statisticChartService;

	/*
	 * @Override public List<Item> getAllItem(int userId) { // TODO
	 * Auto-generated method stub return itemMapper.getAllItem(userId); }
	 */

	@Override
	public ItemAvgData selectItemAvgData(String tableName, String poJoId) {
		
		return itemMapper.selectItemAvgData(tableName, poJoId);
	}

	@Override
	public List<ItemAvgData> selectItemAvgDataByPojoId(String poJoId) {
		List<ItemAvgData> ItemAvgDataList = new LinkedList<ItemAvgData>();
		int poJo_Id = Integer.valueOf(poJoId);
		List<StatisticChart> statisticChartList = statisticChartService.selectByPojoId(poJo_Id);
		for (StatisticChart v : statisticChartList) {
			String tableName = v.getTableName();
			ItemAvgData itemAvgData = selectItemAvgData(tableName, poJoId);
			if (itemAvgData != null) {
				itemAvgData.setDetectionTypeName(v.getDetectionTypeName());
				String avgCurrentData_1 = new DecimalFormat("0.00").format(itemAvgData.getAvgCurrentData());
				double avgCurrentData = Double.parseDouble(avgCurrentData_1);
				itemAvgData.setAvgCurrentData(avgCurrentData);
				String currentLaserChange_1 = new DecimalFormat("0.00").format(itemAvgData.getCurrentLaserChange());
				double currentLaserChange = Double.parseDouble(currentLaserChange_1);
				itemAvgData.setCurrentLaserChange(currentLaserChange);
				String speedChange_1 = new DecimalFormat("0.00").format(itemAvgData.getSpeedChange());
				double speedChange = Double.parseDouble(speedChange_1);
				itemAvgData.setSpeedChange(speedChange);

				String mintoMaxThresholdValue = itemAvgData
						.getMinThresholdValue()
						+ "~"
						+ itemAvgData.getMaxThresholdValue();
				itemAvgData.setMintoMaxThresholdValue(mintoMaxThresholdValue);
				ItemAvgDataList.add(itemAvgData);
			}

		}
		return ItemAvgDataList;
	}

}
