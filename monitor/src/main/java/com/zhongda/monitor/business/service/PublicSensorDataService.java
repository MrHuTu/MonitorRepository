package com.zhongda.monitor.business.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Title:传感器数据接口
 *
 * Description:处理传感器数据增删改查操作
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年3月5日 下午3:08:07
 */
public interface PublicSensorDataService {

	/**
	 * 查询传感器数据
	 * 
	 * @param tableName
	 *            表名
	 * @param sensorNumber
	 *            传感器Id
	 * @param smuNumber
	 *            采集器ID
	 * @param smuChannel
	 *            采集器通道
	 * @param date
	 *            时间区间
	 * @return
	 */
	Map<Object, Object> querySensorData(String tableName, String sensorNumber,
			String smuNumber, String smuChannel, String date);

	/**
	 * 查询传感器数据返回List
	 * 
	 * @param tableName
	 *            表名
	 * @param sensorNumber
	 *            传感器Id
	 * @param smuNumber
	 *            采集器ID
	 * @param smuChannel
	 *            采集器通道
	 * @param response
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param monitorPoint
	 *            测点名称
	 * @param projectName
	 *            项目名称
	 * @param moniterTypeName
	 *            检测指标名称
	 */
	void querySensorDataList(String tableName, String sensorNumber,
			String smuNumber, String smuChannel, HttpServletResponse response,
			String beginDate, String endDate, String monitorPoint,
			String projectName, String moniterTypeName);

}
