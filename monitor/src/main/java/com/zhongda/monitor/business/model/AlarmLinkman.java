package com.zhongda.monitor.business.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AlarmLinkman {
	private Integer alarmLinkmanId;

	private String userName;

	private Integer projectId;

	private String projectName;

	private String phone;

	private String email;

	private Integer status;

	private String itemName;

	private Date createTime;

	private Integer alarmType;

	public Integer getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	private Integer sensorNumber;

	private Integer smuNumber;

	private Integer smuChannel;

	private String  alarmContext;
	
	public String getAlarmContext() {
		return alarmContext;
	}

	public void setAlarmContext(String alarmContext) {
		this.alarmContext = alarmContext;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Integer getSensorNumber() {
		return sensorNumber;
	}

	public void setSensorNumber(Integer sensorNumber) {
		this.sensorNumber = sensorNumber;
	}

	public Integer getSmuNumber() {
		return smuNumber;
	}

	public void setSmuNumber(Integer smuNumber) {
		this.smuNumber = smuNumber;
	}

	public Integer getSmuChannel() {
		return smuChannel;
	}

	public void setSmuChannel(Integer smuChannel) {
		this.smuChannel = smuChannel;
	}

	public Integer getAlarmLinkmanId() {
		return alarmLinkmanId;
	}

	public void setAlarmLinkmanId(Integer alarmLinkmanId) {
		this.alarmLinkmanId = alarmLinkmanId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}