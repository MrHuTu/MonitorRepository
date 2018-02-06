package com.zhongda.monitor.business.model;

import java.util.Date;

public class Terminals {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private String smuId;

    private Integer smuRssi;

    private Integer smuStatus;

    private String smuPowSt;

    private String smuBatSt;

    private String smuVoltage;

    private String smuVersions;

    private Integer timesInterval;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSmuId() {
        return smuId;
    }

    public void setSmuId(String smuId) {
        this.smuId = smuId == null ? null : smuId.trim();
    }

    public Integer getSmuRssi() {
        return smuRssi;
    }

    public void setSmuRssi(Integer smuRssi) {
        this.smuRssi = smuRssi;
    }

    public Integer getSmuStatus() {
        return smuStatus;
    }

    public void setSmuStatus(Integer smuStatus) {
        this.smuStatus = smuStatus;
    }

    public String getSmuPowSt() {
        return smuPowSt;
    }

    public void setSmuPowSt(String smuPowSt) {
        this.smuPowSt = smuPowSt == null ? null : smuPowSt.trim();
    }

    public String getSmuBatSt() {
        return smuBatSt;
    }

    public void setSmuBatSt(String smuBatSt) {
        this.smuBatSt = smuBatSt == null ? null : smuBatSt.trim();
    }

    public String getSmuVoltage() {
        return smuVoltage;
    }

    public void setSmuVoltage(String smuVoltage) {
        this.smuVoltage = smuVoltage == null ? null : smuVoltage.trim();
    }

    public String getSmuVersions() {
        return smuVersions;
    }

    public void setSmuVersions(String smuVersions) {
        this.smuVersions = smuVersions == null ? null : smuVersions.trim();
    }

    public Integer getTimesInterval() {
        return timesInterval;
    }

    public void setTimesInterval(Integer timesInterval) {
        this.timesInterval = timesInterval;
    }
}