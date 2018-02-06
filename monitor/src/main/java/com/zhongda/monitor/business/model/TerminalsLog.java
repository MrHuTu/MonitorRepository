package com.zhongda.monitor.business.model;

import java.util.Date;

public class TerminalsLog {
    private Integer id;

    private String type;

    private String smuId;

    private String smuRssi;

    private String smuStatus;

    private String smuVoltage;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSmuId() {
        return smuId;
    }

    public void setSmuId(String smuId) {
        this.smuId = smuId == null ? null : smuId.trim();
    }

    public String getSmuRssi() {
        return smuRssi;
    }

    public void setSmuRssi(String smuRssi) {
        this.smuRssi = smuRssi == null ? null : smuRssi.trim();
    }

    public String getSmuStatus() {
        return smuStatus;
    }

    public void setSmuStatus(String smuStatus) {
        this.smuStatus = smuStatus == null ? null : smuStatus.trim();
    }

    public String getSmuVoltage() {
        return smuVoltage;
    }

    public void setSmuVoltage(String smuVoltage) {
        this.smuVoltage = smuVoltage == null ? null : smuVoltage.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}