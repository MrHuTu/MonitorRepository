package com.zhongda.monitor.business.model;

public class StatisticChart {
	private Integer statisticChartId;

	private Integer projectId;

	private Integer detectionTypeId;

	private String detectionTypeName;

	private String tableName;

	private String attributes;

	private String sensorId;

	private Threshold threshold;

	public Integer getStatisticChartId() {
		return statisticChartId;
	}

	public void setStatisticChartId(Integer statisticChartId) {
		this.statisticChartId = statisticChartId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getDetectionTypeId() {
		return detectionTypeId;
	}

	public void setDetectionTypeId(Integer detectionTypeId) {
		this.detectionTypeId = detectionTypeId;
	}

	public String getDetectionTypeName() {
		return detectionTypeName;
	}

	public void setDetectionTypeName(String detectionTypeName) {
		this.detectionTypeName = detectionTypeName == null ? null
				: detectionTypeName.trim();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName == null ? null : tableName.trim();
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes == null ? null : attributes.trim();
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId == null ? null : sensorId.trim();
	}

	public Threshold getThreshold() {
		return threshold;
	}

	public void setThreshold(Threshold threshold) {
		this.threshold = threshold;
	}

}