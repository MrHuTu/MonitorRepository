package com.zhongda.monitor.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.SensorMapper;
import com.zhongda.monitor.business.model.Sensor;
import com.zhongda.monitor.business.model.fictitious.MonitorType;
import com.zhongda.monitor.business.service.SensorService;

@Service
public class SensorServiceImpl implements SensorService {

	@Resource
	private SensorMapper sensorMapper;

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
	public boolean addSensor(Sensor sensor) {
		return sensorMapper.insert(sensor)>0;
	}

}
