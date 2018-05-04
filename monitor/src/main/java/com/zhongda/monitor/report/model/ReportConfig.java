package com.zhongda.monitor.report.model;

public class ReportConfig {
	 private int id; 	 
	 private int project_id; 	 
	 private int reportConfig_switch;
	 private String class_path;
	 private String  word_path;
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
	
	public String getClass_path() {
		return class_path;
	}
	public void setClass_path(String class_path) {
		this.class_path = class_path;
	}
	
	public String getWord_path() {
		return word_path;
	}
	public void setWord_path(String word_path) {
		this.word_path = word_path;
	}
	@Override
	public String toString() {
		return "ReportConfig [id=" + id + ", project_id=" + project_id
				+ ", reportConfig_switch=" + reportConfig_switch
				+ ", class_path=" + class_path + "]";
	}
	
	 
}
