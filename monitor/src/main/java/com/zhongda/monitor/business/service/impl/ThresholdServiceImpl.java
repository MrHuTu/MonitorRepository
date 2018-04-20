package com.zhongda.monitor.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.ThresholdMapper;
import com.zhongda.monitor.business.model.Threshold;
import com.zhongda.monitor.business.service.ThresholdService;

@Service
public class ThresholdServiceImpl implements ThresholdService {

	@Resource
	private ThresholdMapper thresholdMapper;

	@Override
	public Map<String, Map<String, Object>> selectThresholdDataByProId(
			Integer projectId) {
		List<Threshold> thresholds = thresholdMapper
				.selectThresholdDataByProId(projectId);
		Map<String, Map<String, Object>> hashMap = new HashMap<String, Map<String, Object>>();
		if (null != thresholds && thresholds.size() > 0) {
			for (Threshold threshold : thresholds) {
				String key = threshold.getMoniterTypeName();
				Map<String, Object> value = hashMap.get(key);
				if (null == value) {

					Map<String, Object> map = new HashMap<String, Object>();
					Double[] a = { threshold.getMinThresholdValue(),
							threshold.getMaxThresholdValue() };
					map.put(threshold.getThresholdValue(), a);

					hashMap.put(threshold.getMoniterTypeName(), map);
				} else {
					Double[] a = { threshold.getMinThresholdValue(),
							threshold.getMaxThresholdValue() };
					value.put(threshold.getThresholdValue(), a);
				}
			}
		}
		return hashMap;
	}
}
