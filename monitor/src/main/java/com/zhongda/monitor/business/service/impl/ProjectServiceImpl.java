package com.zhongda.monitor.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Multiset;
import com.zhongda.monitor.business.mapper.ProjectMapper;
import com.zhongda.monitor.business.mapper.SensorMapper;
import com.zhongda.monitor.business.mapper.StatisticChartMapper;
import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.ProjectSelectCondition;
import com.zhongda.monitor.business.model.Sensor;
import com.zhongda.monitor.business.model.StatisticChart;
import com.zhongda.monitor.business.model.fictitious.MonitorIndicator;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.core.utils.CountUtils;

/**
 * 
 * Title: 项目Service实现类
 *
 * Description:处理项目的增删改查操作
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年3月5日 下午3:06:45
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Resource
	private ProjectMapper projectMapper;

	@Resource
	private SensorMapper sensorMapper;

	@Resource
	private StatisticChartMapper statisticChartMapper;

	/*
	 * @Override public List<Project> loadHome(Integer userId) { List<Project>
	 * ProCharThList = projectMapper .selectProCharThByUserId(userId); for
	 * (Project project : ProCharThList) { List<StatisticChart>
	 * statisticChartList = project .getStatisticChartList(); if (null !=
	 * statisticChartList && statisticChartList.size() > 0) { List<MonitorType>
	 * list = new ArrayList<MonitorType>(); for (StatisticChart statisticChart :
	 * statisticChartList) { List<Sensor> sensorList = sensorMapper
	 * .selectSensorDataByProIdAndMoniType( statisticChart.getTableName(),
	 * project.getProjectId(), statisticChart.getDetectionTypeId()); MonitorType
	 * monitorType = new MonitorType();
	 * monitorType.setMonitorType(statisticChart .getDetectionTypeId());
	 * monitorType.setMonitorTypeName(statisticChart .getDetectionTypeName());
	 * monitorType.setThreshold(statisticChart.getThreshold());
	 * monitorType.setSensorList(sensorList); list.add(monitorType); }
	 * project.setMonitorTypeList(list); project.setStatisticChartList(null); }
	 * 
	 * } return ProCharThList; }
	 */

	@Override
	public Map<String, Object> loadHome(Integer userId) {
		Map<String, Object> projectMap = new HashMap<String, Object>();
		// 创建一个统计监测类型集合
		Multiset<String> monitorTypeMultiset = CountUtils.createMultiset();
		Multiset<String> projectTypeMultiset = CountUtils.createMultiset();
		List<Project> ProCharThList = projectMapper
				.selectProCharThByUserId(userId);
		for (Project project : ProCharThList) {
			System.err.println(project);
			List<StatisticChart> statisticChartList = project
					.getStatisticChartList();
			if (null != statisticChartList && statisticChartList.size() > 0) {
				List<MonitorIndicator> list = new ArrayList<MonitorIndicator>();
				for (StatisticChart statisticChart : statisticChartList) {
					List<Sensor> sensorList = sensorMapper
							.selectSensorDataByProIdAndMoniType(
									statisticChart.getTableName(),
									project.getProjectId(),
									statisticChart.getDetectionTypeId());
					MonitorIndicator monitorType = new MonitorIndicator();
					monitorType.setMonitorType(statisticChart
							.getDetectionTypeId());
					monitorType.setMonitorTypeName(statisticChart
							.getDetectionTypeName());
					monitorType.setThreshold(statisticChart.getThreshold());
					monitorType.setSensorList(sensorList);
					// 将每个项目下的监测类型放入集合中
					monitorTypeMultiset.add(statisticChart
							.getDetectionTypeName());
					list.add(monitorType);
				}
				project.setMonitorTypeList(list);
				project.setStatisticChartList(null);
				// 将每个项目的项目类型放入集合中
				projectTypeMultiset.add(project.getProjectTypeName());
			}
		}
		// 将Multiset转换成Map
		Map<String, Integer> monitorTypeCountMap = CountUtils
				.paserToMap(monitorTypeMultiset);
		Map<String, Integer> projectTypeCountMap = CountUtils
				.paserToMap(projectTypeMultiset);
		projectMap.put("projectList", ProCharThList);
		projectMap.put("monitorTypeCount", monitorTypeCountMap);
		projectMap.put("projectTypeCount", projectTypeCountMap);
		return projectMap;
	}

	@Override
	public Map<String, Object> selectHomeP(Integer userId) {
		Map<String, Object> projectMap = new HashMap<String, Object>();
		HashMap<Integer, List<Sensor>> sensorMap = new HashMap<Integer, List<Sensor>>();
		// 创建一个统计监测类型集合
		Multiset<String> monitorTypeMultiset = CountUtils.createMultiset();
		Multiset<String> projectTypeMultiset = CountUtils.createMultiset();
		// 查找项目及其有关的数据
		List<Project> ProCharThList = projectMapper
				.selectProCharThByUserId(userId);
		// 通过存储过程查找该用户下所有项目的传感器数据
		List<List<Sensor>> sensorLists = sensorMapper.selectHomeP(userId);
		// 把传感器数据组合成key为项目ID，值为该项目下传感器集合的数据结构，方便下一部拿出数据，降低循环次数
		for (List<Sensor> list : sensorLists) {
			sensorMap.put(list.get(0).getProjectId(), list);
		}
		for (Project project : ProCharThList) {
			List<StatisticChart> statisticChartList = project
					.getStatisticChartList();
			if (null != statisticChartList && statisticChartList.size() > 0) {
				List<MonitorIndicator> list = new ArrayList<MonitorIndicator>();
				for (StatisticChart statisticChart : statisticChartList) {
					List<Sensor> sensorList = sensorMap.get(statisticChart
							.getProjectId());
					MonitorIndicator monitorType = new MonitorIndicator();
					monitorType.setMonitorType(statisticChart
							.getDetectionTypeId());
					monitorType.setMonitorTypeName(statisticChart
							.getDetectionTypeName());
					monitorType.setThreshold(statisticChart.getThreshold());
					monitorType.setSensorList(sensorList);
					// 将每个项目下的监测类型放入集合中
					monitorTypeMultiset.add(statisticChart
							.getDetectionTypeName());
					list.add(monitorType);
				}
				project.setMonitorTypeList(list);
				project.setStatisticChartList(null);
				// 将每个项目的项目类型放入集合中
				projectTypeMultiset.add(project.getProjectTypeName());
			}
		}
		// 将Multiset转换成Map
		Map<String, Integer> monitorTypeCountMap = CountUtils
				.paserToMap(monitorTypeMultiset);
		Map<String, Integer> projectTypeCountMap = CountUtils
				.paserToMap(projectTypeMultiset);
		projectMap.put("projectList", ProCharThList);
		projectMap.put("monitorTypeCount", monitorTypeCountMap);
		projectMap.put("projectTypeCount", projectTypeCountMap);
		return projectMap;
	}

	@Override
	public List<Project> queryProjectByUserId(Integer userId) {
		return projectMapper.selectProjectByUserId(userId);
	}

	@Override
	public List<StatisticChart> queryProMonitor(Integer projectId) {

		List<List<StatisticChart>> selectLastData = statisticChartMapper
				.selectLastData(projectId);
		ArrayList<StatisticChart> statisticCharts = new ArrayList<StatisticChart>();
		for (List<StatisticChart> list : selectLastData) {
			statisticCharts.add(list.get(0));
		}
		return statisticCharts;
	}

	@Override
	public List<Project> getAllProject(ProjectSelectCondition userId) {
		return projectMapper.getAllProject(userId);
	}

	@Override
	public Project selectByPrimaryKey(String projectId) {
		
		return projectMapper.selectByPrimaryKey(Integer.valueOf(projectId));
	}

}
