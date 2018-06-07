package com.zhongda.monitor.report.model.fictitious;

/**
 * 记录周报数据
 * @author 胡超
 * 2018年6月7日08:48:57
 *
 */
public class ReportWeekData_Dates {
	//累计变化量
	private String totalLaserChange;
	
	private String time;

	public String getTotalLaserChange() {
		
		return totalLaserChange;
	
	}

	public void setTotalLaserChange(String totalLaserChange) {
		
	
		this.totalLaserChange = totalLaserChange;
	}

	public String getTime() {
		
		return	time;
		
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "ReportWeekData_Dates [totalLaserChange=" + totalLaserChange
				+ ", time=" + time + "]";
	}
	
	
	
}
