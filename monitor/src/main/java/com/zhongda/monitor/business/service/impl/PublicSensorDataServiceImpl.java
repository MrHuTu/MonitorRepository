package com.zhongda.monitor.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.PublicSensorDataMapper;
import com.zhongda.monitor.business.model.fictitious.DataEchart;
import com.zhongda.monitor.business.model.fictitious.PublicSensorData;
import com.zhongda.monitor.business.service.PublicSensorDataService;
import com.zhongda.monitor.business.utils.JxlExcelUtils;

/**
 * 
 * Title:传感器数据Service实现类
 *
 * Description:处理传感器数据增删改查操作
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年3月5日 下午3:09:27
 */
@Service
public class PublicSensorDataServiceImpl implements PublicSensorDataService {

	@Resource
	private PublicSensorDataMapper pSenDataMapper;

	@Resource
	private JxlExcelUtils jxlExcel;

	@Override
	public Map<Object, Object> querySensorData(String tableName,
			String sensorNumber, String smuNumber, String smuChannel,
			String date) {
		long time2 = new Date().getTime();
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DATE, +1);
		String beginDate = format.format(today);
		String endDate = format.format(instance.getTime());
		if ("aweek".equals(date)) {
			instance.add(Calendar.DATE, -7);
			instance.add(Calendar.DATE, -1);
			beginDate = format.format(instance.getTime());
		} else if ("amonth".equals(date)) {
			instance.add(Calendar.MONTH, -1);
			instance.add(Calendar.DATE, -1);
			beginDate = format.format(instance.getTime());
		} else if ("ayear".equals(date)) {
			instance.add(Calendar.YEAR, -1);
			instance.add(Calendar.DATE, -1);
			beginDate = format.format(instance.getTime());
		}
		System.out.println("begindate:" + beginDate + "  endDate:" + endDate);
		List<PublicSensorData> sensorDatas = pSenDataMapper
				.selectSenDataByDate(tableName, sensorNumber, smuNumber,
						smuChannel, beginDate, endDate);
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
		long time = new Date().getTime();
		System.out.println("耗时：" + (time - time2) + "ms");
		return hashMap;
	}

	@Override
	public void querySensorDataList(String tableName, String sensorNumber,
			String smuNumber, String smuChannel, HttpServletResponse response,
			String beginDate, String endDate, String monitorPoint,
			String projectName, String moniterTypeName) {
		System.out.println("detectionName:" + tableName);
		System.out.println("------------------------------------");
		System.out.println("smuCmsId:" + smuNumber + "smuCmsChannel:"
				+ smuChannel);
		System.out.println("------------------------------------");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<PublicSensorData> list = pSenDataMapper.selectSenDataByDate(
				tableName, sensorNumber, smuNumber, smuChannel, beginDate,
				endDate);
		String datesnew = format.format(list.get(0).getCurrentTimes());
		// response.setContentType("application/octet-stream");
		// response.setContentType("application/OCTET-STREAM;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ datesnew + ".xls");
		String[] head = { "初次测试值(MM)", "前次测试时间", "前次测试值(MM)", "本次检测时间",
				"本次测试值(MM)", "单次变化量(MM)", "总变化量(MM)", "变化速率(MM/MIN)" };
		if (null != list.get(0).getCurrentTemperature()) {
			head = new String[] { "初次测试值(MM)", "前次测试时间", "前次测试值(MM)", "本次检测时间",
					"本次测试值(MM)", "单次变化量(MM)", "总变化量(MM)", "变化速率(MM/MIN)",
					"温度(℃)" };
		}
		jxlExcel.export_excel(response, list, head, projectName,
				moniterTypeName, monitorPoint);
	}

}
