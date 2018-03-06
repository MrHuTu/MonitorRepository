package com.zhongda.monitor.business.model.fictitious;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DataEchart {

	private Date date;

	private Double currentData;

	private Double value;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getCurrentData() {
		return currentData;
	}

	public void setCurrentData(Double currentData) {
		this.currentData = currentData;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
