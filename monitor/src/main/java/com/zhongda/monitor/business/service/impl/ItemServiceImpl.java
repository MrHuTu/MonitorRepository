package com.zhongda.monitor.business.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.business.mapper.ItemMapper;
import com.zhongda.monitor.business.model.ItemAvgData;
import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.ProjectSelectCondition;
import com.zhongda.monitor.business.model.StatisticChart;
import com.zhongda.monitor.business.service.ItemService;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.business.service.StatisticChartService;
import com.zhongda.monitor.core.model.Result;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemMapper itemMapper;
	@Autowired
	StatisticChartService statisticChartService;

	@Resource
	private ProjectService projectService;

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

				
				String mintoMaxThresholdValue = itemAvgData.getMinThresholdValue()+ "~"+ itemAvgData.getMaxThresholdValue();
				
				itemAvgData.setMintoMaxThresholdValue(mintoMaxThresholdValue);
				
				ItemAvgDataList.add(itemAvgData);
			}

		}
		return ItemAvgDataList;
	}

	@Override
	public Result<List<ItemAvgData>> packagItemLeftData(String poJoId) {

		List<ItemAvgData> ItemAvgData = selectItemAvgDataByPojoId(poJoId);
		
		Iterator<ItemAvgData> itemAvg = ItemAvgData.iterator();
		// 查询用户下的项目
		
		User user = ShiroUtils.getCurrentUser();
		
		int userId = user.getUserId();
		
		ProjectSelectCondition projectSelectCondition = new ProjectSelectCondition(String.valueOf(userId));
		
		List<Project> currenUserOfProject = projectService.getAllProject(projectSelectCondition);
		
		ListIterator<Project> projectList = currenUserOfProject.listIterator();
		
		
		
		while (projectList.hasNext()) {
			
			Project project = projectList.next();
			
			if (String.valueOf(project.getProjectId()).equals(poJoId)) {
				
				String projectName = project.getProjectName();
				
				String projectPrincipal = project.getProjectPrincipal();
				
				int projectSensorNumber = project.getProjectSensorNumber();
				
				int projectSmuNumber = project.getProjectSmuNumber();
				
				int alCount = project.getAlCount();
				
				Date projectBeginTime = project.getProjectBeginTime();
				
				String projectTypeName = project.getProjectTypeName();
				
				String projectAddress = project.getProjectAddress();
								
				while (itemAvg.hasNext()) {
					
					ItemAvgData itemAvgData = itemAvg.next();
					
					itemAvgData.setProjectName(projectName);
					
					itemAvgData.setProjectPrincipal(projectPrincipal);
					
					itemAvgData.setProjectSensorNumber(projectSensorNumber);
					
					itemAvgData.setProjectSmuNumber(projectSmuNumber);
					
					itemAvgData.setProjectTypeName(projectTypeName);
					
					itemAvgData.setAlCount(alCount);
					
					itemAvgData.setProjectBeginTime(projectBeginTime);
					
					itemAvgData.setProjectAddress(projectAddress);
				}
			}

		}
		return new Result<List<ItemAvgData>>().setCode(Result.SUCCESS).setMsg("操作成功").setData(ItemAvgData);
	}

}
