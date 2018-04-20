package com.zhongda.monitor.business.model;

public class Threshold {
	private Integer thresholdId;

	private Integer projectId;

	private Integer projectType;

	private Integer monitorType;

	private Integer thresholdType;

	private Double minThresholdValue;

	private Double maxThresholdValue;

	private Double minDrasticThresholdValue;

	private Double maxDrasticThresholdValue;

	private String thresholdValue;// 数据库没有字段，阈值类型名称

	private String moniterTypeName;// 数据库没有字段,检测指标名称

	public Integer getThresholdId() {
		return thresholdId;
	}

	public void setThresholdId(Integer thresholdId) {
		this.thresholdId = thresholdId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public Integer getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(Integer monitorType) {
		this.monitorType = monitorType;
	}

	public Integer getThresholdType() {
		return thresholdType;
	}

	public void setThresholdType(Integer thresholdType) {
		this.thresholdType = thresholdType;
	}

	public Double getMinThresholdValue() {
		return minThresholdValue;
	}

	public void setMinThresholdValue(Double minThresholdValue) {
		this.minThresholdValue = minThresholdValue;
	}

	public Double getMaxThresholdValue() {
		return maxThresholdValue;
	}

	public void setMaxThresholdValue(Double maxThresholdValue) {
		this.maxThresholdValue = maxThresholdValue;
	}

	public Double getMinDrasticThresholdValue() {
		return minDrasticThresholdValue;
	}

	public void setMinDrasticThresholdValue(Double minDrasticThresholdValue) {
		this.minDrasticThresholdValue = minDrasticThresholdValue;
	}

	public Double getMaxDrasticThresholdValue() {
		return maxDrasticThresholdValue;
	}

	public void setMaxDrasticThresholdValue(Double maxDrasticThresholdValue) {
		this.maxDrasticThresholdValue = maxDrasticThresholdValue;
	}

	public String getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(String thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public String getMoniterTypeName() {
		return moniterTypeName;
	}

	public void setMoniterTypeName(String moniterTypeName) {
		this.moniterTypeName = moniterTypeName;
	}
}