package com.zhongda.monitor.report.model.fictitious;

import org.joda.time.DateTime;

import com.zhongda.monitor.report.utils.FillWordMapUtils;

public class ReportWeekData_Dates {
	//累计变化量
	private double totalLaserChange;
	
	private String time;

	public String getTotalLaserChange() {
		
		return FillWordMapUtils.formData(totalLaserChange);
	
	}

	public void setTotalLaserChange(double totalLaserChange) {
		
	
		this.totalLaserChange = totalLaserChange;
	}

	public String getTime() {
		
		return	new DateTime(time).toString("YYYY/MM/dd");
		
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
