package com.zhongda.monitor.business.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@ApiModel(value = "告警信息对象")
public class Alarm implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "告警信息ID")
	private Integer alarmId;

	@ApiModelProperty(value = "用户ID", hidden = true)
	private Integer userId;

	@ApiModelProperty(value = "用户名" )
	private String userName;

	@ApiModelProperty(value = "项目ID" , hidden = true)
	private Integer projectId;

	@ApiModelProperty(value = "项目名")
	private String projectName;

	@ApiModelProperty(value = "测点名")
	private String monitorPoint;

	@ApiModelProperty(value = "采集器编号")
	private String smuNumber;

	@ApiModelProperty(value = "采集器通道", hidden = true)
	private String smuChannel;

	@ApiModelProperty(value = "传感器编号")
	private String sensorNumber;

	@ApiModelProperty(value = "告警类型")
	private Integer alarmType;// 告警类型( 1:设备类告警 ; 2:数据类告警)

	@ApiModelProperty(value = "告警內容")
	private String alarmContext;

	@ApiModelProperty(value = "创建时间" , hidden = true)
	private Date createTime;

	@ApiModelProperty(value = "告警状态")
	private Integer alarmStatus;// ( 0:未确认 ; 1:已确认)

	@ApiModelProperty(value = "告警等级" , hidden = true)
	private Integer alarmLevel;// 默认为1

	@ApiModelProperty(value = "同类消息产生次数" , hidden = true)
	private Integer frequency;
	
	@ApiModelProperty(value = "告警类型名", hidden = true)
	private String alarmTypeName;// 告警类型名字 数据库没有
	
	@ApiModelProperty(value = "告警状态名", hidden = true)
	private String alarmStatusName;// 告警状态名

	// 数据库不存在该字段，只作为查询时判断查询条件创建时间是否在该范围内时使用
	@ApiModelProperty(value = "根据时间查询的起始时间")
	private Date startCreateTime;

	// 数据库不存在该字段，只作为查询时判断查询条件创建时间是否在该范围内时使用
	@ApiModelProperty(value = "根据时间查询的借宿时间")
	private Date endCreateTime;

	// 数据库不存在该字段，只作为分页时存储当前页数据时使用
	@ApiModelProperty(value = "分页信息当前页面")
	private Integer pageNum;

	// 数据库不存在该字段，只作为分页时存储每页记录条数数据时使用
	@ApiModelProperty(value = "分页信息一个的条数")
	private Integer pageSize;

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

	public Date getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getAlarmStatusName() {
		return alarmStatusName;
	}

	public void setAlarmStatusName(String alarmStatusName) {
		this.alarmStatusName = alarmStatusName;
	}

	public String getAlarmTypeName() {
		return alarmTypeName;
	}

	public void setAlarmTypeName(String alarmTypeName) {
		this.alarmTypeName = alarmTypeName;
	}

	public Integer getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

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

	public String getMonitorPoint() {
		return monitorPoint;
	}

	public void setMonitorPoint(String monitorPoint) {
		this.monitorPoint = monitorPoint == null ? null : monitorPoint.trim();
	}

	public String getSmuNumber() {
		return smuNumber;
	}

	public void setSmuNumber(String smuNumber) {
		this.smuNumber = smuNumber == null ? null : smuNumber.trim();
	}

	public String getSmuChannel() {
		return smuChannel;
	}

	public void setSmuChannel(String smuChannel) {
		this.smuChannel = smuChannel == null ? null : smuChannel.trim();
	}

	public String getSensorNumber() {
		return sensorNumber;
	}

	public void setSensorNumber(String sensorNumber) {
		this.sensorNumber = sensorNumber == null ? null : sensorNumber.trim();
	}

	public Integer getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	public String getAlarmContext() {
		return alarmContext;
	}

	public void setAlarmContext(String alarmContext) {
		this.alarmContext = alarmContext == null ? null : alarmContext.trim();
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(Integer alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	public Integer getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(Integer alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
}