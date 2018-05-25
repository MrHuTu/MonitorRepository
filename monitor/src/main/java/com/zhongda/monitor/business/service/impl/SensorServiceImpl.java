package com.zhongda.monitor.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongda.monitor.business.mapper.SensorMapper;
import com.zhongda.monitor.business.mapper.StatisticChartMapper;
import com.zhongda.monitor.business.model.Sensor;
import com.zhongda.monitor.business.model.StatisticChart;
import com.zhongda.monitor.business.model.fictitious.MonitorType;
import com.zhongda.monitor.business.service.SensorService;
import com.zhongda.monitor.management.model.PaginationResult;

@Service
public class SensorServiceImpl implements SensorService {

	@Resource
	private SensorMapper sensorMapper;

	@Resource
	private StatisticChartMapper statisticChartMapper;

	@Override
	public List<MonitorType> selectSensorByPro(Integer projectId) {
		List<Sensor> snesorList = sensorMapper.selectSensorByPro(projectId);
		Map<String, List<Sensor>> hashMap = new HashMap<String, List<Sensor>>();
		for (Sensor sensor : snesorList) {
			String key = sensor.getMonitorTypeName();
			List<Sensor> value = hashMap.get(key);
			if (null == value) {
				value = new ArrayList<Sensor>();
			}
			value.add(sensor);
			hashMap.put(key, value);
		}
		List<MonitorType> arrayList = new ArrayList<MonitorType>();
		for (Map.Entry<String, List<Sensor>> entry : hashMap.entrySet()) {
			MonitorType monitorType = new MonitorType();
			monitorType.setMonitorTypeName(entry.getKey());
			monitorType.setSensorList(entry.getValue());
			arrayList.add(monitorType);
		}
		return arrayList;
	}

	@Override
	@Transactional
	public boolean addSensor(Sensor sensor) {
		if (statisticChartMapper.selectCountByproAndMT(sensor.getProjectId(),
				sensor.getMonitorType()) <= 0) {
			StatisticChart statisticChart = new StatisticChart();
			statisticChart.setProjectId(sensor.getProjectId());
			statisticChart.setDetectionTypeId(sensor.getMonitorType());
			statisticChart.setDetectionTypeName(sensor.getMonitorTypeName());
			statisticChart.setTableName(sensor.getTableName());
			statisticChart.setAttributes(sensor.getAttributes());
			if (sensorMapper.insert(sensor) > 0
					&& statisticChartMapper.insertSelective(statisticChart) > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (sensorMapper.insert(sensor) > 0) {
				return true;
			} else {
				return false;
			}
		}

	}

	@Override
	public PaginationResult selectSensorByProjectId(Integer projectId,
			Integer monitorType, int offset, int limit, String condition) {
		Page<Object> offsetPage = PageHelper.offsetPage(offset, limit);
		List<Sensor> sensors = null;
		if (condition.length() > 0 && null != condition) {
			sensors = sensorMapper.selectSensorSearchByManage(projectId,
					monitorType, condition);
		} else {
			sensors = sensorMapper.selectSensorByProjectId(projectId,
					monitorType, null, null, null);
		}
		return new PaginationResult(offsetPage.getTotal(), sensors);
	}

	@Override
	public List<Sensor> selectMonitorTypeByPro(Integer projectId) {
		return sensorMapper.selectMonitorTypeByPro(projectId);
	}

	@Override
	@Transactional
	public boolean deleteSensorInfo(Integer sensorId, Integer monitorType,
			Integer projectId) {
		if (sensorMapper.deleteByPrimaryKey(sensorId) > 0) {
			if (sensorMapper
					.selectSensorCountByProAndMT(projectId, monitorType) <= 0) {
				if (statisticChartMapper.deleteStatisChaByProAndMT(projectId,
						monitorType) > 0) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateByPrimaryKeySelective(Sensor sensor) {
		return sensorMapper.updateByPrimaryKeySelective(sensor) > 0 ? true
				: false;
	}
}
