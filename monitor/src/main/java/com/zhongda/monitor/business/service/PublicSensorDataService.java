package com.zhongda.monitor.business.service;

import java.util.Map;

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
}
