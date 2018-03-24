package com.zhongda.monitor.business.model.fictitious;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("接收项目信息的实体类")
public class Item {
	
	@ApiModelProperty("项目ID")
	private int project_id;
	
	@ApiModelProperty("项目名称")
	private String project_name;
	
	@ApiModelProperty("负责人")
	private String real_name;
	
	@ApiModelProperty("创建时间")
	private Date project_begin_time;
	
	@ApiModelProperty("结束时间")
	private Date project_end_time;
	
	@ApiModelProperty("项目类型")
	private String project_type;
	
	@ApiModelProperty("项目地址")
	private String project_address;
	
	@ApiModelProperty("项目描述")
	private String project_description;
	
	@ApiModelProperty("项目详情")
	private String project_description_;
	
	@ApiModelProperty("项目状态")
	private String project_status;
	
	@ApiModelProperty("告警数")
	private String project_alarmNum;
	
	@ApiModelProperty("采集器数量")
	private int project_sensorNum;
	
	@ApiModelProperty("传感器数量")
	private int project_smuNum;

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getProject_begin_time() {
		return project_begin_time;
	}

	public void setProject_begin_time(Date project_begin_time) {
		this.project_begin_time = project_begin_time;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getProject_end_time() {
		return project_end_time;
	}

	public void setProject_end_time(Date project_end_time) {
		this.project_end_time = project_end_time;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public String getProject_address() {
		return project_address;
	}

	public void setProject_address(String project_address) {
		this.project_address = project_address;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}

	public String getProject_description_() {
		return project_description_;
	}

	public void setProject_description_(String project_description_) {
		this.project_description_ = project_description_;
	}

	public String getProject_status() {
		return project_status;
	}

	public void setProject_status(String project_status) {
		this.project_status = project_status;
	}

	public String getProject_alarmNum() {
		return project_alarmNum;
	}

	public void setProject_alarmNum(String project_alarmNum) {
		this.project_alarmNum = project_alarmNum;
	}

	public int getProject_sensorNum() {
		return project_sensorNum;
	}

	public void setProject_sensorNum(int project_sensorNum) {
		this.project_sensorNum = project_sensorNum;
	}

	public int getProject_smuNum() {
		return project_smuNum;
	}

	public void setProject_smuNum(int project_smuNum) {
		this.project_smuNum = project_smuNum;
	}

	@Override
	public String toString() {
		return "Item [project_id=" + project_id + ", project_name="
				+ project_name + ", real_name=" + real_name
				+ ", project_begin_time=" + project_begin_time
				+ ", project_end_time=" + project_end_time + ", project_type="
				+ project_type + ", project_address=" + project_address
				+ ", project_description=" + project_description
				+ ", project_description_=" + project_description_
				+ ", project_status=" + project_status + ", project_alarmNum="
				+ project_alarmNum + ", project_sensorNum=" + project_sensorNum
				+ ", project_smuNum=" + project_smuNum + "]";
	}

}
