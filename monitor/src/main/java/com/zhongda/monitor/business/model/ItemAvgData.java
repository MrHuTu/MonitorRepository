package com.zhongda.monitor.business.model;

public class ItemAvgData {
	/***
	 * 测点类型
	 */
private String detectionTypeName;
//测点当前平均数据
private double avgCurrentData;
//测点当前平均改变数据
private double currentLaserChange;

private double  speedChange;

private double minThresholdValue;

private double MaxThresholdValue;

private String mintoMaxThresholdValue;

public String getDetectionTypeName() {
	return detectionTypeName;
}

public void setDetectionTypeName(String detectionTypeName) {
	this.detectionTypeName = detectionTypeName;
}

public double getAvgCurrentData() {
	return avgCurrentData;
}

public void setAvgCurrentData(double avgCurrentData) {
	this.avgCurrentData = avgCurrentData;
}

public double getCurrentLaserChange() {
	return currentLaserChange;
}

public void setCurrentLaserChange(double currentLaserChange) {
	this.currentLaserChange = currentLaserChange;
}

public double getSpeedChange() {
	return speedChange;
}

public void setSpeedChange(double speedChange) {
	this.speedChange = speedChange;
}

public double getMinThresholdValue() {
	return minThresholdValue;
}

public void setMinThresholdValue(double minThresholdValue) {
	this.minThresholdValue = minThresholdValue;
}

public double getMaxThresholdValue() {
	return MaxThresholdValue;
}

public void setMaxThresholdValue(double maxThresholdValue) {
	MaxThresholdValue = maxThresholdValue;
}

public String getMintoMaxThresholdValue() {
	return mintoMaxThresholdValue;
}

public void setMintoMaxThresholdValue(String mintoMaxThresholdValue) {
	this.mintoMaxThresholdValue = mintoMaxThresholdValue;
}

@Override
public String toString() {
	return "ItemAvgData [detectionTypeName=" + detectionTypeName
			+ ", avgCurrentData=" + avgCurrentData + ", currentLaserChange="
			+ currentLaserChange + ", speedChange=" + speedChange
			+ ", minThresholdValue=" + minThresholdValue
			+ ", MaxThresholdValue=" + MaxThresholdValue + "]";
}







}
