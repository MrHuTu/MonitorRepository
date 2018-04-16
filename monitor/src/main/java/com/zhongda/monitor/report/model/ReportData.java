package com.zhongda.monitor.report.model;

import java.util.Date;
/**
 * 用来做数数据迁移，报表数据提取的实体类
 * @author Administrator
 *
 */
public class ReportData {
	//private int id;
	private int project_id;
	private Date current_times;
	private double first_data;
	private double current_data;
	private double current_laser_change;
	private double total_laser_change;
	private int detection_type_id;
	

	/*public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
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
	public int getDetection_type_id() {
		return detection_type_id;
	}
	public void setDetection_type_id(int detection_type_id) {
		this.detection_type_id = detection_type_id;
	}
	@Override
	public String toString() {
		return "ReportData [id="  + ", project_id=" + project_id
				+ ", current_times=" + current_times + ", first_data="
				+ first_data + ", current_data=" + current_data
				+ ", current_laser_change=" + current_laser_change
				+ ", total_laser_change=" + total_laser_change
				+ ", detection_type_id=" + detection_type_id + "]";
	}
	


	
}
