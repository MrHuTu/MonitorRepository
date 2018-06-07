package com.zhongda.monitor.report.model.fictitious;

import java.util.List;

/**
 * 周报bean
 * @author huchao
 * 2018年6月5日16:09:26
 *
 */
public class ReportWeekData_Head{
	
	/*//工程名称直接到样式里面查询
	String projectName;*/
	
	//传感器编号
	String sensorNumber;
	
	//终端号
	String smuNumber;
	
	//采集器通道
	String smuChannel;
	
	//测点	
	String monitorPoint;
	
	//项目id
	private String projectName;
	
	//具体数据
	List<ReportWeekData_Dates> reportWeekDataDates;

	public String getSensorNumber() {
		return sensorNumber;
	}

	public void setSensorNumber(String sensorNumber) {
		this.sensorNumber = sensorNumber;
	}

	public String getSmuNumber() {
		return smuNumber;
	}

	public void setSmuNumber(String smuNumber) {
		this.smuNumber = smuNumber;
	}

	public String getSmuChannel() {
		return smuChannel;
	}

	public void setSmuChannel(String smuChannel) {
		this.smuChannel = smuChannel;
	}

	public String getMonitorPoint() {
		return monitorPoint;
	}

	public void setMonitorPoint(String monitorPoint) {
		this.monitorPoint = monitorPoint;
	}

	public List<ReportWeekData_Dates> getReportWeekDataDates() {
		return reportWeekDataDates;
	}

	public void setReportWeekDataDates(List<ReportWeekData_Dates> reportWeekDataDates) {
		this.reportWeekDataDates = reportWeekDataDates;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "ReportWeekData_Head [sensorNumber=" + sensorNumber
				+ ", smuNumber=" + smuNumber + ", smuChannel=" + smuChannel
				+ ", monitorPoint=" + monitorPoint + ", projectName="
				+ projectName + ", reportWeekDataDates=" + reportWeekDataDates
				+ "]";
	}
	
	
	
	
	

}
