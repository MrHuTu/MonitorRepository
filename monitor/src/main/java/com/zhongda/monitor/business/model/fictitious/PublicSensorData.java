package com.zhongda.monitor.business.model.fictitious;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PublicSensorData {

	private Integer id;

	private String sensorNumber;

	private Date firstTime;

	private Double firstData;

	private Date previousTime;

	private Double previousData;

	private Date currentTimes;

	private Double currentData;

	private Double currentTemperature;

	private Double currentLaserChange;

	private Double totalLaserChange;

	private Double speedChange;

	private Integer sensorStatus;

	private String createType;

	private String smuNumber;

	private String smuChannel;

	private Integer smuStatus;

	private String beginTimes;

	private String endTimes;

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
		this.sensorNumber = sensorNumber;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

	public Double getCurrentTemperature() {
		return currentTemperature;
	}

	public void setCurrentTemperature(Double currentTemperature) {
		this.currentTemperature = currentTemperature;
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
		this.createType = createType;
	}

	public String getSmuNumber() {
		return smuNumber;
	}

	public void setSmuNumber(String smuNumber) {
		this.smuNumber = smuNumber;
	}

	public String getSmuChannel() {
		return smuChannel;
	}

	public void setSmuChannel(String smuChannel) {
		this.smuChannel = smuChannel;
	}

	public Integer getSmuStatus() {
		return smuStatus;
	}

	public void setSmuStatus(Integer smuStatus) {
		this.smuStatus = smuStatus;
	}

	public String getBeginTimes() {
		return beginTimes;
	}

	public void setBeginTimes(String beginTimes) {
		this.beginTimes = beginTimes;
	}

	public String getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}

	@Override
	public String toString() {
		return "PublicSensorData [id=" + id + ", sensorNumber=" + sensorNumber
				+ ", firstTime=" + firstTime + ", firstData=" + firstData
				+ ", previousTime=" + previousTime + ", previousData="
				+ previousData + ", currentTimes=" + currentTimes
				+ ", currentData=" + currentData + ", currentTemperature="
				+ currentTemperature + ", currentLaserChange="
				+ currentLaserChange + ", totalLaserChange=" + totalLaserChange
				+ ", speedChange=" + speedChange + ", sensorStatus="
				+ sensorStatus + ", createType=" + createType + ", smuNumber="
				+ smuNumber + ", smuChannel=" + smuChannel + ", smuStatus="
				+ smuStatus + ", beginTimes=" + beginTimes + ", endTimes="
				+ endTimes + "]";
	}

}