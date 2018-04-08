package com.zhongda.monitor.business.model.fictitious;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ItemLeft {
	private String projectName;

	private Date projectBeginTime;

	private String projectPrincipal; //项目负责人

	private int projectSensorNumber;//传感器数量

	private int projectSmuNumber;//传感器数量

	private int alCount;// 项目告警次数 数据库没有字段

	private String projectTypeName;// 项目类型名称

	private String projectAddress;
	
	private String weatherAddress; //专用于天气接口的地址，只包含市级名称
	
	private String projectDescription;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getProjectBeginTime() {
		return projectBeginTime;
	}

	public void setProjectBeginTime(Date projectBeginTime) {
		this.projectBeginTime = projectBeginTime;
	}

	public String getProjectPrincipal() {
		return projectPrincipal;
	}

	public void setProjectPrincipal(String projectPrincipal) {
		this.projectPrincipal = projectPrincipal;
	}

	public int getProjectSensorNumber() {
		return projectSensorNumber;
	}

	public void setProjectSensorNumber(int projectSensorNumber) {
		this.projectSensorNumber = projectSensorNumber;
	}

	public int getProjectSmuNumber() {
		return projectSmuNumber;
	}

	public void setProjectSmuNumber(int projectSmuNumber) {
		this.projectSmuNumber = projectSmuNumber;
	}

	public int getAlCount() {
		return alCount;
	}

	public void setAlCount(int alCount) {
		this.alCount = alCount;
	}

	public String getProjectTypeName() {
		return projectTypeName;
	}

	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}
	
	public String getWeatherAddress() {
		return weatherAddress;
	}

	public void setWeatherAddress(String weatherAddress) {
		this.weatherAddress = weatherAddress;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	@Override
	public String toString() {
		return "ItemLeft [projectName=" + projectName + ", projectBeginTime="
				+ projectBeginTime + ", projectPrincipal=" + projectPrincipal
				+ ", projectSensorNumber=" + projectSensorNumber
				+ ", projectSmuNumber=" + projectSmuNumber + ", alCount="
				+ alCount + ", projectTypeName=" + projectTypeName
				+ ", projectAddress=" + projectAddress + "]";
	}
	
	
}
