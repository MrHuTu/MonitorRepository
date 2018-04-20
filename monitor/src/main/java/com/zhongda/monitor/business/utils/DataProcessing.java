package com.zhongda.monitor.business.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.zhongda.monitor.business.model.fictitious.DataEchart;
import com.zhongda.monitor.business.model.fictitious.PublicSensorData;

@Component
public class DataProcessing {

	/**
	 * 时间处理
	 * 
	 * @param date
	 * @return
	 */
	public Map<String, String> timeProcessing(String date) {

		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar instance = Calendar.getInstance();
		String beginTime = dateFormat.format(today);
		instance.add(Calendar.DATE, +1);
		String endTime = dateFormat.format(instance.getTime());
		if ("aweek".equals(date)) {
			instance.add(Calendar.DATE, -7);
			instance.add(Calendar.DATE, -1);
			beginTime = dateFormat.format(instance.getTime());
		} else if ("amonth".equals(date)) {
			instance.add(Calendar.MONTH, -1);
			instance.add(Calendar.DATE, -1);
			beginTime = dateFormat.format(instance.getTime());
		} else if ("ayear".equals(date)) {
			instance.add(Calendar.YEAR, -1);
			instance.add(Calendar.DATE, -1);
			beginTime = dateFormat.format(instance.getTime());
		}
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("beginTime", beginTime);
		hashMap.put("endTime", endTime);
		return hashMap;

	}

	/**
	 * 填充数据倒叙排列
	 * 
	 * @param list
	 * @return
	 */
	public Map<String, PublicSensorData> benchmarkProcessing(
			List<PublicSensorData> list) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:SS");
		Calendar instance = Calendar.getInstance();
		PublicSensorData container = null;
		// 填充数据所在集合
		ArrayList<PublicSensorData> pubArrayList = new ArrayList<PublicSensorData>();
		Map<String, PublicSensorData> hashMap = new HashMap<String, PublicSensorData>();
		if (null != list && list.size() > 0) {
			for (PublicSensorData publicSensorData : list) {
				// 本次测试时间和上次测试时间的差值
				long jetLag = (publicSensorData.getCurrentTimes().getTime() - publicSensorData
						.getPreviousTime().getTime()) / (1000 * 60);
				// 当差值大于15代表传感器之前没有传数据
				if (jetLag > 15) {
					instance.setTime(publicSensorData.getPreviousTime());
					// 对传感器没穿的数据进行填充
					for (int i = 0; i < ((int) Math.rint(jetLag / 10.0)) - 1; i++) {
						instance.add(Calendar.MINUTE, +10);
						PublicSensorData fillingData = new PublicSensorData();
						// 如果第一条数据之前有数据未填充，则不对之前数据进行填充
						if (container == null) {
							System.out.println("第一个值出现问题");
							break;
						} else {
							// 拿上一次数据对缺省数据填充
							fillingData = fillingData(fillingData, container);
						}
						// 填充时间，时间间隔10分钟
						fillingData.setCurrentTimes(instance.getTime());
						pubArrayList.add(fillingData);
					}
				}
				container = publicSensorData;
			}
			// 把填充数据放入原集合
			for (PublicSensorData publicSensorData : pubArrayList) {
				list.add(publicSensorData);
			}
			// 数据结构处理，key：yyyy-MM-dd HH:m0:00 格式时间key，方便查询
			for (PublicSensorData publicSensorData : list) {
				String oldTime = simpleDateFormat.format(publicSensorData
						.getCurrentTimes());
				String newTime = oldTime.substring(0, 15) + "0:00";
				hashMap.put(newTime, publicSensorData);
			}
		}
		return hashMap;
	}

	/**
	 * 减去基准点数据
	 * 
	 * @param oriDatas
	 * @param BMDatas
	 * @return
	 */
	public List<PublicSensorData> differenceData(
			Map<String, PublicSensorData> oriDatas,
			Map<String, PublicSensorData> BMDatas) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		// 保留1位小数
		nf.setMaximumFractionDigits(1);
		// 如果不需要四舍五入，可以使用RoundingMode.DOWN
		nf.setRoundingMode(RoundingMode.UP);
		ArrayList<PublicSensorData> arrayList = new ArrayList<PublicSensorData>();
		// 目标传感器减去基准点传感器数据
		for (Map.Entry<String, PublicSensorData> entry : oriDatas.entrySet()) {
			// 如果基准点没有当前时间数据，则不处理当前时间数据
			if (null != BMDatas.get(entry.getKey())) {
				entry.getValue().setCurrentData(
						Double.parseDouble(new DecimalFormat("#.0")
								.format(entry.getValue().getCurrentData()
										- BMDatas.get(entry.getKey())
												.getCurrentData())));
				entry.getValue().setCurrentLaserChange(
						Double.parseDouble(new DecimalFormat("#.0")
								.format(entry.getValue()
										.getCurrentLaserChange()
										- BMDatas.get(entry.getKey())
												.getCurrentLaserChange())));
				entry.getValue().setTotalLaserChange(
						Double.parseDouble(new DecimalFormat("#.0")
								.format(entry.getValue().getTotalLaserChange()
										- BMDatas.get(entry.getKey())
												.getTotalLaserChange())));
				entry.getValue().setSpeedChange(
						Double.parseDouble(new DecimalFormat("#.0")
								.format(entry.getValue().getSpeedChange()
										- BMDatas.get(entry.getKey())
												.getSpeedChange())));
			}
			arrayList.add(entry.getValue());

		}

		// 升序
		Collections.sort(arrayList, new Comparator<PublicSensorData>() {

			@Override
			public int compare(PublicSensorData o1, PublicSensorData o2) {
				// 按时间升序
				if (o1.getCurrentTimes().getTime() < o2.getCurrentTimes()
						.getTime()) {
					return 1;
				} else if (o1.getCurrentTimes().getTime() == o2
						.getCurrentTimes().getTime()) {
					return 0;
				}
				return -1;
			}

		});
		return arrayList;

	}

	/**
	 * 组装数据给前台
	 * 
	 * @param sensorDatas
	 * @return
	 */
	public Map<Object, Object> formatAssembly(List<PublicSensorData> sensorDatas) {
		Map<Object, Object> hashMap = new HashMap<Object, Object>();
		ArrayList<DataEchart> currentLaserChangeList = new ArrayList<DataEchart>();// 单次变化
		ArrayList<DataEchart> totalLaserChangeList = new ArrayList<DataEchart>();// 总变化量
		ArrayList<DataEchart> speedChangeList = new ArrayList<DataEchart>();// 变化速率speedChange
		for (PublicSensorData publicSensorData : sensorDatas) {
			Date time = publicSensorData.getCurrentTimes();
			Double currentData = publicSensorData.getCurrentData();

			DataEchart currentLaser = new DataEchart();
			DataEchart totalLaserChange = new DataEchart();
			DataEchart speedChange = new DataEchart();

			currentLaser.setDate(time);
			currentLaser.setCurrentData(currentData);
			currentLaser.setValue(publicSensorData.getCurrentLaserChange());
			currentLaserChangeList.add(currentLaser);

			totalLaserChange.setDate(time);
			totalLaserChange.setCurrentData(currentData);
			totalLaserChange.setValue(publicSensorData.getTotalLaserChange());
			totalLaserChangeList.add(totalLaserChange);

			speedChange.setDate(time);
			speedChange.setCurrentData(currentData);
			speedChange.setValue(publicSensorData.getSpeedChange());
			speedChangeList.add(speedChange);

		}
		hashMap.put("currentLaserChange", currentLaserChangeList);
		hashMap.put("totalLaserChange", totalLaserChangeList);
		hashMap.put("speedChange", speedChangeList);
		hashMap.put("sensorData", sensorDatas);
		return hashMap;
	}

	/**
	 * 填充新数据
	 * 
	 * @param isNullPSD
	 * @param publicSensorData
	 * @return
	 */
	private PublicSensorData fillingData(PublicSensorData isNullPSD,
			PublicSensorData publicSensorData) {
		isNullPSD.setSensorNumber(publicSensorData.getSensorNumber());
		isNullPSD.setFirstTime(publicSensorData.getFirstTime());
		isNullPSD.setFirstData(publicSensorData.getFirstData());
		isNullPSD.setPreviousTime(publicSensorData.getPreviousTime());
		isNullPSD.setPreviousData(publicSensorData.getPreviousData());
		isNullPSD.setCurrentData(publicSensorData.getCurrentData());
		isNullPSD.setCurrentTemperature(publicSensorData
				.getCurrentTemperature());
		isNullPSD.setCurrentLaserChange(publicSensorData
				.getCurrentLaserChange());
		isNullPSD.setTotalLaserChange(publicSensorData.getTotalLaserChange());
		isNullPSD.setSpeedChange(publicSensorData.getSpeedChange());
		isNullPSD.setSensorStatus(publicSensorData.getSensorStatus());
		isNullPSD.setCreateType(publicSensorData.getCreateType());
		isNullPSD.setSmuNumber(publicSensorData.getSmuNumber());
		isNullPSD.setSmuChannel(publicSensorData.getSmuChannel());
		isNullPSD.setSmuStatus(publicSensorData.getSmuStatus());
		return isNullPSD;
	}
}
