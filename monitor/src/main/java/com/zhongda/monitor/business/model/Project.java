package com.zhongda.monitor.business.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zhongda.monitor.business.model.fictitious.MonitorIndicator;

@JsonInclude(Include.NON_NULL)
public class Project {
	private Integer projectId;

	private String projectName;

	private Integer projectType;

	private String projectAddress;

	private String projectLongitude;

	private String projectLatitude;

	private Date projectBeginTime;

	private Date projectEndTime;

	private Integer projectStatus;

	private String projectDescription;
	
	private String projectPrincipal; //项目负责人
	
	private Integer projectSensorNumber;//传感器数量
	
	private Integer projectSmuNumber;//传感器数量

	private Integer alCount;// 项目告警次数 数据库没有字段

	private String projectTypeName;// 项目类型名称
	
	private String projectStatusName;// 项目状态字符串标识

	private List<MonitorIndicator> monitorTypeList; // 检测指标对象集合

	private List<StatisticChart> statisticChartList;// 一个强大的表、很难解释自己看数据看意会 -by
													// 胡超

	private List<Sensor> sensorList;// 传感器集合

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName == null ? null : projectName.trim();
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress == null ? null : projectAddress
				.trim();
	}

	public String getProjectLongitude() {
		return projectLongitude;
	}

	public void setProjectLongitude(String projectLongitude) {
		this.projectLongitude = projectLongitude == null ? null
				: projectLongitude.trim();
	}

	public String getProjectLatitude() {
		return projectLatitude;
	}

	public void setProjectLatitude(String projectLatitude) {
		this.projectLatitude = projectLatitude == null ? null : projectLatitude
				.trim();
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getProjectBeginTime() {
		return projectBeginTime;
	}

	public void setProjectBeginTime(Date projectBeginTime) {
		this.projectBeginTime = projectBeginTime;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getProjectEndTime() {
		return projectEndTime;
	}

	public void setProjectEndTime(Date projectEndTime) {
		this.projectEndTime = projectEndTime;
	}

	public Integer getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription == null ? null
				: projectDescription.trim();
	}

	public Integer getAlCount() {
		return alCount;
	}

	public void setAlCount(Integer alCount) {
		this.alCount = alCount;
	}

	public String getProjectTypeName() {
		return projectTypeName;
	}

	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}

	public List<StatisticChart> getStatisticChartList() {
		return statisticChartList;
	}

	public void setStatisticChartList(List<StatisticChart> statisticChartList) {
		this.statisticChartList = statisticChartList;
	}

	public List<MonitorIndicator> getMonitorTypeList() {
		return monitorTypeList;
	}

	public void setMonitorTypeList(List<MonitorIndicator> monitorTypeList) {
		this.monitorTypeList = monitorTypeList;
	}

	public List<Sensor> getSensorList() {
		return sensorList;
	}

	public void setSensorList(List<Sensor> sensorList) {
		this.sensorList = sensorList;
	}

	public String getProjectPrincipal() {
		return projectPrincipal;
	}

	public void setProjectPrincipal(String projectPrincipal) {
		this.projectPrincipal = projectPrincipal;
	}

	public Integer getProjectSensorNumber() {
		return projectSensorNumber;
	}

	public void setProjectSensorNumber(Integer projectSensorNumber) {
		this.projectSensorNumber = projectSensorNumber;
	}

	public Integer getProjectSmuNumber() {
		return projectSmuNumber;
	}

	public void setProjectSmuNumber(Integer projectSmuNumber) {
		this.projectSmuNumber = projectSmuNumber;
	}

	public String getProjectStatusName() {
		return projectStatusName;
	}

	public void setProjectStatusName(String projectStatusName) {
		this.projectStatusName = projectStatusName;
	}

}