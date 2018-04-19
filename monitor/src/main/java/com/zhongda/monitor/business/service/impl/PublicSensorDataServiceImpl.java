package com.zhongda.monitor.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.PublicSensorDataMapper;
import com.zhongda.monitor.business.model.fictitious.PublicSensorData;
import com.zhongda.monitor.business.service.PublicSensorDataService;
import com.zhongda.monitor.business.utils.DataProcessing;
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

	@Resource
	private DataProcessing dataProcessing;

	@Override
	public Map<Object, Object> querySensorData(String tableName,
			String sensorNumber, String smuNumber, String smuChannel,
			String date) {
		Map<String, String> timeProcessing = dataProcessing
				.timeProcessing(date);

		return dataProcessing.formatAssembly(pSenDataMapper
				.selectSenDataByDate(tableName, sensorNumber, smuNumber,
						smuChannel, timeProcessing.get("beginTime"),
						timeProcessing.get("endTime")));

	}

	@Override
	public void querySensorDataList(String tableName, String sensorNumber,
			String smuNumber, String smuChannel, HttpServletResponse response,
			String beginDate, String endDate, String monitorPoint,
			String projectName, String moniterTypeName) {
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

	@Override
	public Map<Object, Object> selectSenDataforBenchmark(String tableName,
			String sensorNumber, String smuNumber, String smuChannel,
			String sensorNumberBM, String smuNumberBM, String smuChannelBM,
			String date) {
		Map<String, String> timeProcessing = dataProcessing
				.timeProcessing(date);
		List<List<PublicSensorData>> datas = pSenDataMapper
				.selectSenDataforBenchmark(tableName, sensorNumber, smuNumber,
						smuChannel, sensorNumberBM, smuNumberBM, smuChannelBM,
						timeProcessing.get("beginTime"),
						timeProcessing.get("endTime"));
		Map<String, PublicSensorData> fillingDatas = dataProcessing
				.benchmarkProcessing(datas.get(0));
		Map<String, PublicSensorData> fillingDataBMs = dataProcessing
				.benchmarkProcessing(datas.get(1));
		return dataProcessing.formatAssembly(dataProcessing.differenceData(
				fillingDatas, fillingDataBMs));
	}

}
