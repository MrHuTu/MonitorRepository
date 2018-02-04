package com.zhongda.monitor.business.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhongda.monitor.core.model.SysDictionary;

public class Project {
	private Integer projectId;

	private Integer userId;

	private String projectName;

	private Integer projectTypeId;

	private String projectAddress;

	private String projectLongitude;

	private String projectLatitude;

	private Date projectBeginTime;

	private Date projectEndTime;

	private Integer projectStatus;

	private String projectDescription;

	// 数据库无
	private SysDictionary sysDictionary;

	// 缓存字典表查到的状态字符串
	private String projectStatusString;
	
	//数据库不存在该字段，只作为分页时存储当前页数据时使用
    private Integer pageNum;
    
    //数据库不存在该字段，只作为分页时存储每页记录条数数据时使用
    private Integer pageSize;
    
    //缓存测点数量，传感器数量，阀值数量，图片数量
    //异常测点数，异常传感器数量，未处理告警信息数量，异常采集器数量
    private Integer alarmTerminalsCount;
    private Integer alarmAlarmCount;
	private Integer alarmSensorInfoCount;
	private Integer alarmDetectionCount;
    private Integer detectionPointCount;
	private Integer sensorInfoCount;
    private Integer thresholdCount;
    private Integer imageCount;
    
    //缓存采集器的数量。
    private Integer terminalsCount;
    
    // 缓存告警信息的数量
  	private Integer alarmCount;
  	
  	public Integer getAlarmTerminalsCount() {
 		return alarmTerminalsCount;
 	}

 	public void setAlarmTerminalsCount(Integer alarmTerminalsCount) {
 		this.alarmTerminalsCount = alarmTerminalsCount;
 	}
  	
  	public Integer getAlarmAlarmCount() {
		return alarmAlarmCount;
	}

	public void setAlarmAlarmCount(Integer alarmAlarmCount) {
		this.alarmAlarmCount = alarmAlarmCount;
	}
  	
  	public Integer getAlarmSensorInfoCount() {
		return alarmSensorInfoCount;
	}

	public void setAlarmSensorInfoCount(Integer alarmSensorInfoCount) {
		this.alarmSensorInfoCount = alarmSensorInfoCount;
	}
  	
  	public Integer getAlarmDetectionCount() {
		return alarmDetectionCount;
	}

	public void setAlarmDetectionCount(Integer alarmDetectionCount) {
		this.alarmDetectionCount = alarmDetectionCount;
	}
     
    public Integer getTerminalsCount() {
		return terminalsCount;
	}

	public void setTerminalsCount(Integer terminalsCount) {
		this.terminalsCount = terminalsCount;
	}

	public Integer getDetectionPointCount() {
		return detectionPointCount;
	}

	public void setDetectionPointCount(Integer detectionPointCount) {
		this.detectionPointCount = detectionPointCount;
	}

	public Integer getSensorInfoCount() {
		return sensorInfoCount;
	}

	public void setSensorInfoCount(Integer sensorInfoCount) {
		this.sensorInfoCount = sensorInfoCount;
	}

	public Integer getThresholdCount() {
		return thresholdCount;
	}

	public void setThresholdCount(Integer thresholdCount) {
		this.thresholdCount = thresholdCount;
	}

	public Integer getImageCount() {
		return imageCount;
	}

	public void setImageCount(Integer imageCount) {
		this.imageCount = imageCount;
	}

	public String getProjectStatusString() {
		return projectStatusString;
	}

	public void setProjectStatusString(String projectStatusString) {
		this.projectStatusString = projectStatusString == null ? null : projectStatusString.trim();
	}

	public Integer getAlarmCount() {
		return alarmCount;
	}

	public void setAlarmCount(Integer alarmCount) {
		this.alarmCount = alarmCount;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName == null ? null : projectName.trim();
	}

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
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

	public void setProjectBeginTime(Date projectBeginTime) {
		this.projectBeginTime = projectBeginTime;
	}

	public Integer getProjectStatus() {
		return projectStatus;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getProjectBeginTime() {
		return projectBeginTime;
	}

	public void setProjectEndTime(Date projectEndTime) {
		this.projectEndTime = projectEndTime;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getProjectEndTime() {
		return projectEndTime;
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

	public SysDictionary getSysDictionary() {
		return sysDictionary;
	}

	public void setSysDictionary(SysDictionary sysDictionary) {
		this.sysDictionary = sysDictionary;
	}
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}