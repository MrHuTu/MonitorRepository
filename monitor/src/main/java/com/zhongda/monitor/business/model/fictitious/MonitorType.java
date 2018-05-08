package com.zhongda.monitor.business.model.fictitious;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zhongda.monitor.business.model.Sensor;

/**
 * 
 * Title: 安卓 /androidPro/monitorType类
 *
 * Description:
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年5月7日 下午5:17:24
 */
@JsonInclude(Include.NON_NULL)
public class MonitorType {

	private String monitorTypeName;

	private List<Sensor> sensorList;

	public String getMonitorTypeName() {
		return monitorTypeName;
	}

	public void setMonitorTypeName(String monitorTypeName) {
		this.monitorTypeName = monitorTypeName;
	}

	public List<Sensor> getSensorList() {
		return sensorList;
	}

	public void setSensorList(List<Sensor> sensorList) {
		this.sensorList = sensorList;
	}

}
