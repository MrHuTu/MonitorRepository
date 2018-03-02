package com.zhongda.monitor.business.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Sensor {
	private Integer sensorId;

	private Integer projectId;

	private String monitorPoint;

	private Integer monitorType;

	private String smuNumber;

	private String smuChannel;

	private String sensorNumber;

	private String sensorType;

	private String sensorModel;

	private String sensorLongitude;

	private String sensorLatitude;

	private String sensorPlace;

	private Float sensorDepth;

	private String itemName;// 数据库sys_code字段

	private PublicSensorData publicSensorData;// 传感器数据公共对象 数据库没有字段

	public Integer getSensorId() {
		return sensorId;
	}

	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getMonitorPoint() {
		return monitorPoint;
	}

	public void setMonitorPoint(String monitorPoint) {
		this.monitorPoint = monitorPoint == null ? null : monitorPoint.trim();
	}

	public Integer getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(Integer monitorType) {
		this.monitorType = monitorType;
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

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType == null ? null : sensorType.trim();
	}

	public String getSensorModel() {
		return sensorModel;
	}

	public void setSensorModel(String sensorModel) {
		this.sensorModel = sensorModel == null ? null : sensorModel.trim();
	}

	public String getSensorLongitude() {
		return sensorLongitude;
	}

	public void setSensorLongitude(String sensorLongitude) {
		this.sensorLongitude = sensorLongitude == null ? null : sensorLongitude
				.trim();
	}

	public String getSensorLatitude() {
		return sensorLatitude;
	}

	public void setSensorLatitude(String sensorLatitude) {
		this.sensorLatitude = sensorLatitude == null ? null : sensorLatitude
				.trim();
	}

	public String getSensorPlace() {
		return sensorPlace;
	}

	public void setSensorPlace(String sensorPlace) {
		this.sensorPlace = sensorPlace == null ? null : sensorPlace.trim();
	}

	public Float getSensorDepth() {
		return sensorDepth;
	}

	public void setSensorDepth(Float sensorDepth) {
		this.sensorDepth = sensorDepth;
	}

	public PublicSensorData getPublicSensorData() {
		return publicSensorData;
	}

	public void setPublicSensorData(PublicSensorData publicSensorData) {
		this.publicSensorData = publicSensorData;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}