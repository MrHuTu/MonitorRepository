package com.zhongda.monitor.business.model;

public class UserProject {
	private Integer upId;

	private Integer userId;

	private Integer projectId;

	private String userName;

	private String projectName;

	private Boolean leaderFlag;

	public Integer getUpId() {
		return upId;
	}

	public void setUpId(Integer upId) {
		this.upId = upId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Boolean getLeaderFlag() {
		return leaderFlag;
	}

	public void setLeaderFlag(Boolean leaderFlag) {
		this.leaderFlag = leaderFlag;
	}
}