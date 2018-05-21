package com.zhongda.monitor.report.model;

public class ReportContentDay {
	//项目ID
	private String projectId;
	
	//主要监测人员
	private String reportMain;
	
	//报告编写人
	private String reportWrite;
	
	//报告审批人
	private String reportApproval;
	
	//报告批准人
	private String reportRatify;
	
	//工程概况
	private String projectSurvey;
	
	//监测成果 分析总结
	private String projectAchievement;
	
	//建议
	private String projectSuggest;
	
	//总变化量图片路径
	private String totalLaserChangePic;
	
	//单词变化量图片路径
	private String currentLaserChangePic;
	
	//变化速率图片路径
	private String speedChangePic;
	
	//测点图
	private String monitorPoitPic;
	

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getReportMain() {
		return reportMain;
	}

	public void setReportMain(String reportMain) {
		this.reportMain = reportMain;
	}

	public String getReportWrite() {
		return reportWrite;
	}

	public void setReportWrite(String reportWrite) {
		this.reportWrite = reportWrite;
	}

	public String getReportApproval() {
		return reportApproval;
	}

	public void setReportApproval(String reportApproval) {
		this.reportApproval = reportApproval;
	}

	public String getReportRatify() {
		return reportRatify;
	}

	public void setReportRatify(String reportRatify) {
		this.reportRatify = reportRatify;
	}

	public String getProjectSurvey() {
		return projectSurvey;
	}

	public void setProjectSurvey(String projectSurvey) {
		this.projectSurvey = projectSurvey;
	}

	public String getProjectAchievement() {
		return projectAchievement;
	}

	public void setProjectAchievement(String projectAchievement) {
		this.projectAchievement = projectAchievement;
	}

	public String getProjectSuggest() {
		return projectSuggest;
	}

	public void setProjectSuggest(String projectSuggest) {
		this.projectSuggest = projectSuggest;
	}

	public String getTotalLaserChangePic() {
		return totalLaserChangePic;
	}

	public void setTotalLaserChangePic(String totalLaserChangePic) {
		this.totalLaserChangePic = totalLaserChangePic;
	}

	public String getCurrentLaserChangePic() {
		return currentLaserChangePic;
	}

	public void setCurrentLaserChangePic(String currentLaserChangePic) {
		this.currentLaserChangePic = currentLaserChangePic;
	}

	public String getSpeedChangePic() {
		return speedChangePic;
	}

	public void setSpeedChangePic(String speedChangePic) {
		this.speedChangePic = speedChangePic;
	}

	public String getMonitorPoitPic() {
		return monitorPoitPic;
	}

	public void setMonitorPoitPic(String monitorPoitPic) {
		this.monitorPoitPic = monitorPoitPic;
	}

	@Override
	public String toString() {
		return "ReportContentDay [projectId=" + projectId + ", reportMain="
				+ reportMain + ", reportWrite=" + reportWrite
				+ ", reportApproval=" + reportApproval + ", reportRatify="
				+ reportRatify + ", projectSurvey=" + projectSurvey
				+ ", projectAchievement=" + projectAchievement
				+ ", projectSuggest=" + projectSuggest
				+ ", totalLaserChangePic=" + totalLaserChangePic
				+ ", currentLaserChangePic=" + currentLaserChangePic
				+ ", speedChangePic=" + speedChangePic + ", monitorPoitPic="
				+ monitorPoitPic + "]";
	}
	

}
