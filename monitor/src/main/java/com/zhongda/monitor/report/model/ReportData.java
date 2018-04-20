package com.zhongda.monitor.report.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/**
 * 用来做数数据迁移，报表数据提取的实体类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class ReportData implements Serializable{
	private int id;
	private int project_id;
	private Date  first_time;	
	private Date current_times;	
	
	private double first_data;
	private double current_data;
	private double current_laser_change;
	private double total_laser_change;
	private int monitor_type;
	
	
	private String  smu_number;
	private String  sensor_number;
	private String smu_channel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCurrent_times() {
		return current_times;
	}
	public void setCurrent_times(Date current_times) {
		this.current_times = current_times;
	}
	public double getFirst_data() {
		return first_data;
	}
	public void setFirst_data(double first_data) {
		this.first_data = first_data;
	}
	public double getCurrent_data() {
		return current_data;
	}
	public void setCurrent_data(double current_data) {
		this.current_data = current_data;
	}
	public double getCurrent_laser_change() {
		return current_laser_change;
	}
	public void setCurrent_laser_change(double current_laser_change) {
		this.current_laser_change = current_laser_change;
	}
	public double getTotal_laser_change() {
		return total_laser_change;
	}
	public void setTotal_laser_change(double total_laser_change) {
		this.total_laser_change = total_laser_change;
	}
	
	
	public int getMonitor_type() {
		return monitor_type;
	}
	public void setMonitor_type(int monitor_type) {
		this.monitor_type = monitor_type;
	}
	public String getSmu_number() {
		return smu_number;
	}
	public void setSmu_number(String smu_number) {
		this.smu_number = smu_number;
	}
	public String getSensor_number() {
		return sensor_number;
	}
	public void setSensor_number(String sensor_number) {
		this.sensor_number = sensor_number;
	}
	public String getSmu_channel() {
		return smu_channel;
	}
	public void setSmu_channel(String smu_channel) {
		this.smu_channel = smu_channel;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getFirst_time() {
		return first_time;
	}
	public void setFirst_time(Date first_time) {
		this.first_time = first_time;
	}
	
	
	
	@Override
	public String toString() {
		return "ReportData [project_id=" + project_id + ", current_times="
				+ current_times + ", first_time=" + first_time
				+ ", first_data=" + first_data + ", current_data="
				+ current_data + ", current_laser_change="
				+ current_laser_change + ", total_laser_change="
				+ total_laser_change + ", monitor_type=" + monitor_type
				+ ", smu_number=" + smu_number + ", sensor_number="
				+ sensor_number + ", smu_channel=" + smu_channel + "]";
	}
	
	
	
	


	
}
