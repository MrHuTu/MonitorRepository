package com.zhongda.monitor.business.model;

import java.util.Date;

public class LaserDataShow {
    private Integer id;

    private String sensorNumber;

    private Date firstTime;

    private Double firstData;

    private Date previousTime;

    private Double previousData;

    private Date currentTimes;

    private Double currentData;

    private Double currentLaserChange;

    private Double totalLaserChange;

    private Double speedChange;

    private Integer sensorStatus;

    private String createType;

    private String smuNumber;

    private String smuChannel;

    private Integer smuStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSensorNumber() {
        return sensorNumber;
    }

    public void setSensorNumber(String sensorNumber) {
        this.sensorNumber = sensorNumber == null ? null : sensorNumber.trim();
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Double getFirstData() {
        return firstData;
    }

    public void setFirstData(Double firstData) {
        this.firstData = firstData;
    }

    public Date getPreviousTime() {
        return previousTime;
    }

    public void setPreviousTime(Date previousTime) {
        this.previousTime = previousTime;
    }

    public Double getPreviousData() {
        return previousData;
    }

    public void setPreviousData(Double previousData) {
        this.previousData = previousData;
    }

    public Date getCurrentTimes() {
        return currentTimes;
    }

    public void setCurrentTimes(Date currentTimes) {
        this.currentTimes = currentTimes;
    }

    public Double getCurrentData() {
        return currentData;
    }

    public void setCurrentData(Double currentData) {
        this.currentData = currentData;
    }

    public Double getCurrentLaserChange() {
        return currentLaserChange;
    }

    public void setCurrentLaserChange(Double currentLaserChange) {
        this.currentLaserChange = currentLaserChange;
    }

    public Double getTotalLaserChange() {
        return totalLaserChange;
    }

    public void setTotalLaserChange(Double totalLaserChange) {
        this.totalLaserChange = totalLaserChange;
    }

    public Double getSpeedChange() {
        return speedChange;
    }

    public void setSpeedChange(Double speedChange) {
        this.speedChange = speedChange;
    }

    public Integer getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(Integer sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType == null ? null : createType.trim();
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

    public Integer getSmuStatus() {
        return smuStatus;
    }

    public void setSmuStatus(Integer smuStatus) {
        this.smuStatus = smuStatus;
    }
}