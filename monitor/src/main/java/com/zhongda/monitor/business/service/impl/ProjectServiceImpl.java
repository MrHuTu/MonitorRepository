package com.zhongda.monitor.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.ProjectMapper;
import com.zhongda.monitor.business.mapper.SensorMapper;
import com.zhongda.monitor.business.model.MonitorType;
import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.model.Sensor;
import com.zhongda.monitor.business.model.StatisticChart;
import com.zhongda.monitor.business.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Resource
	private ProjectMapper projectMapper;

	@Resource
	private SensorMapper sensorMapper;

	@Override
	public List<Project> loadHome(Integer userId) {
		List<Project> ProCharThList = projectMapper
				.selectProCharThByUserId(userId);
		for (Project project : ProCharThList) {
			List<StatisticChart> statisticChartList = project
					.getStatisticChartList();
			if (null != statisticChartList && statisticChartList.size() > 0) {
				List<MonitorType> list = new ArrayList<MonitorType>();
				for (StatisticChart statisticChart : statisticChartList) {
					List<Sensor> sensorList = sensorMapper
							.selectSensorDataByProIdAndMoniType(
									statisticChart.getTableName(),
									project.getProjectId(),
									statisticChart.getDetectionTypeId());
					MonitorType monitorType = new MonitorType();
					monitorType.setMonitorType(statisticChart
							.getDetectionTypeId());
					monitorType.setMonitorTypeName(statisticChart
							.getDetectionTypeName());
					monitorType.setThreshold(statisticChart.getThreshold());
					monitorType.setSensorList(sensorList);
					list.add(monitorType);
				}
				project.setMonitorTypeList(list);
				project.setStatisticChartList(null);
			}

		}
		return ProCharThList;
	}

}
