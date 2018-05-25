package com.zhongda.monitor.business.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zhongda.monitor.business.model.fictitious.PublicSensorData;

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
	 * 
	 * @param tableName
	 *            表名
	 * @param sensorNumber
	 *            传感器Id
	 * @param smuNumber
	 *            采集器ID
	 * @param smuChannel
	 *            采集器通道
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	List<PublicSensorData> querySensorDatas(String tableName,
			String sensorNumber, String smuNumber, String smuChannel,
			String beginTime, String endTime);

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

	/**
	 * 
	 * @param tableName
	 *            表名
	 * @param sensorNumber
	 *            传感器编号
	 * @param smuNumber
	 *            采集器编号
	 * @param smuChannel
	 *            采集器通道
	 * @param sensorNumberBM
	 *            基准点传感器编号
	 * @param smuNumberBM
	 *            基准点采集器编号
	 * @param smuChannelBM
	 *            基准点传采集器通道
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	Map<Object, Object> selectSenDataforBenchmark(String tableName,
			String sensorNumber, String smuNumber, String smuChannel,
			String sensorNumberBM, String smuNumberBM, String smuChannelBM,
			String date);

	/**
	 * 修改数据表第一次数据
	 * 
	 * @param tableName
	 * @param smuCmsId
	 * @param beginTimes
	 * @param endTimes
	 * @param sensorNumber
	 * @return
	 */
	boolean updatefirstData(String tableName, String smuCmsId,
			String beginTimes, String endTimes, String sensorNumber);

}
