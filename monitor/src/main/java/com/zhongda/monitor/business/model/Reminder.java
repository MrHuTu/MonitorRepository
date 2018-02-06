package com.zhongda.monitor.business.model;

import java.util.Date;

public class Reminder {
    private Integer reminderId;

    private String smuNumber;

    private String smuChannel;

    private String sensorNumber;

    private String reminderContext;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer frequency;

    private String status;

    public Integer getReminderId() {
        return reminderId;
    }

    public void setReminderId(Integer reminderId) {
        this.reminderId = reminderId;
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

    public String getReminderContext() {
        return reminderContext;
    }

    public void setReminderContext(String reminderContext) {
        this.reminderContext = reminderContext == null ? null : reminderContext.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}