package com.zhongda.monitor.business.model;

public class ProjectSelectCondition {
private String  userId;

public ProjectSelectCondition(String userId) {
	
	this.userId = userId;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}


}
