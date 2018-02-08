package com.zhongda.monitor.business.model;

import java.util.Date;
import java.util.List;

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

	private Integer alCount;// 项目告警次数 数据库没有字段

	private String projectTypeName;// 项目类型名称

	private List<MonitorType> monitorTypeList; // 检测指标对象集合

	private List<StatisticChart> statisticChartList;// 一个强大的表、很难解释自己看数据看意会 -by
													// 胡超

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

	public Date getProjectBeginTime() {
		return projectBeginTime;
	}

	public void setProjectBeginTime(Date projectBeginTime) {
		this.projectBeginTime = projectBeginTime;
	}

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

	public List<MonitorType> getMonitorTypeList() {
		return monitorTypeList;
	}

	public void setMonitorTypeList(List<MonitorType> monitorTypeList) {
		this.monitorTypeList = monitorTypeList;
	}

}