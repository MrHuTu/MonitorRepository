package com.zhongda.monitor.business.model.fictitious;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zhongda.monitor.business.model.Sensor;
import com.zhongda.monitor.business.model.Threshold;

/**
 * <p>
 * 检测指标实体类，代表一个项目下的一个检测指标对象
 * </p>
 * 
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @sine 2018年3月5日
 */
@JsonInclude(Include.NON_NULL)
public class MonitorIndicator {
	private Integer monitorType;

	private String monitorTypeName;

	private String tableName;

	private Threshold threshold;

	private List<Sensor> sensorList;

	public Integer getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(Integer monitorType) {
		this.monitorType = monitorType;
	}

	public String getMonitorTypeName() {
		return monitorTypeName;
	}

	public void setMonitorTypeName(String monitorTypeName) {
		this.monitorTypeName = monitorTypeName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Threshold getThreshold() {
		return threshold;
	}

	public void setThreshold(Threshold threshold) {
		this.threshold = threshold;
	}

	public List<Sensor> getSensorList() {
		return sensorList;
	}

	public void setSensorList(List<Sensor> sensorList) {
		this.sensorList = sensorList;
	}

	@Override
	public String toString() {
		return "MonitorIndicator [monitorType=" + monitorType
				+ ", monitorTypeName=" + monitorTypeName + ", tableName="
				+ tableName + ", threshold=" + threshold + ", sensorList="
				+ sensorList + "]";
	}

}