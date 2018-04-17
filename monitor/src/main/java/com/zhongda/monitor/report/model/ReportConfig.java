package com.zhongda.monitor.report.model;

public class ReportConfig {
	 private int id; 	 
	 private int project_id; 	 
	 private int reportConfig_switch;
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
	public int getReportConfig_switch() {
		return reportConfig_switch;
	}
	public void setReportConfig_switch(int reportConfig_switch) {
		this.reportConfig_switch = reportConfig_switch;
	}
	@Override
	public String toString() {
		return "ReportConfig [id=" + id + ", project_id=" + project_id
				+ ", reportConfig_switch=" + reportConfig_switch + "]";
	} 
	 
}
