package com.zhongda.monitor.report.model.fictitious;
/**
 * 各项目对应的监测参数
 * @author 胡超  2018年4月18日11:12:55
 *
 */
public class ProjectPara {
	private  int project_id;
	private  int monitor_type;
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getMonitor_type() {
		return monitor_type;
	}
	public void setMonitor_type(int monitor_type) {
		this.monitor_type = monitor_type;
	}
	@Override
	public String toString() {
		return "ProjectPara [project_id=" + project_id + ", monitor_type="
				+ monitor_type + "]";
	}
	
	
}
