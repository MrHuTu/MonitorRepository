package com.zhongda.monitor.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.business.model.fictitious.PublicSensorData;

public interface PublicSensorDataMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(PublicSensorData record);

	int insertSelective(PublicSensorData record);

	PublicSensorData selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(PublicSensorData record);

	int updateByPrimaryKey(PublicSensorData record);

	/**
	 * 查询数据库正在使用的所有采集器
	 * 
	 * @param tableName
	 * @return
	 */
	List<PublicSensorData> selectSmuidGroupBySmuId(
			@Param(value = "tableName") String tableName);

	/**
	 * 查询传感器数据
	 * 
	 * @param tableName
	 *            表名
	 * @param sensorNumber
	 *            传感器编号
	 * @param smuNumber
	 *            采集器编号
	 * @param smuChannel
	 *            采集器通道
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 传感器数据List集合
	 */
	List<PublicSensorData> selectSenDataByDate(String tableName,
			@Param(value = "sensorId") String sensorNumber,
			@Param(value = "smuNumber") String smuNumber,
			@Param(value = "smuChannel") String smuChannel,
			@Param(value = "beginDate") String beginDate,
			@Param(value = "endDate") String endDate);

	/**
	 * 查询传感器数据升序
	 * 
	 * @param tableName
	 *            表名
	 * @param sensorNumber
	 *            传感器编号
	 * @param smuNumber
	 *            采集器编号
	 * @param smuChannel
	 *            采集器通道
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 传感器数据List集合
	 */
	List<PublicSensorData> selectSenDataByDateAdroid(String tableName,
			@Param(value = "sensorId") String sensorNumber,
			@Param(value = "smuNumber") String smuNumber,
			@Param(value = "smuChannel") String smuChannel,
			@Param(value = "beginDate") String beginDate,
			@Param(value = "endDate") String endDate);

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
	List<List<PublicSensorData>> selectSenDataforBenchmark(
			@Param(value = "tableName") String tableName,
			@Param(value = "sensorId") String sensorNumber,
			@Param(value = "smuNumber") String smuNumber,
			@Param(value = "smuChannel") String smuChannel,
			@Param(value = "sensorNumberBM") String sensorNumberBM,
			@Param(value = "smuNumberBM") String smuNumberBM,
			@Param(value = "smuChannelBM") String smuChannelBM,
			@Param(value = "beginDate") String beginDate,
			@Param(value = "endDate") String endDate);

	/**
	 * 查询传感器数据通过传入条件
	 * 
	 * @param publicSensorData
	 * @return
	 */
	List<PublicSensorData> selectSensorDataByAllContia(String tableName,
			@Param(value = "sensorNumber") String sensorNumber,
			@Param(value = "beginTimes") String beginTimes,
			@Param(value = "endTimes") String endTimes,
			@Param(value = "smuNumber") String smuNumber,
			@Param(value = "smuChannel") String smuChannel);

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
	int updatefirstData(String tableName, @Param("smuCmsId") String smuCmsId,
			@Param("beginTimes") String beginTimes,
			@Param("endTimes") String endTimes,
			@Param("sensorNumber") String sensorNumber);

}