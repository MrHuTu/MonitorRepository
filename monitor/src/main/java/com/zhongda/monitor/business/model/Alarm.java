package com.zhongda.monitor.business.model;

import java.util.Date;

public class Alarm {
    private Integer alarmId;

    private Integer userId;

    private String userName;

    private Integer projectId;

    private String projectName;

    private Integer detectionId;

    private String smuNumber;

    private String smuChannel;

    private String sensorNumber;

    private Integer alarmType;

    private String alarmContext;

    private Date createTime;

    private Integer alarmStatus;

    private Integer alarmLevel;

    private Integer frequency;

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

    public Integer getDetectionId() {
        return detectionId;
    }

    public void setDetectionId(Integer detectionId) {
        this.detectionId = detectionId;
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