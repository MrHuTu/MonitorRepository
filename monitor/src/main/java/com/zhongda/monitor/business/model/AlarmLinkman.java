package com.zhongda.monitor.business.model;

public class AlarmLinkman {
    private Integer alarmLinkmanId;

    private String userName;

    private Integer projectId;

    private String projectName;

    private String phone;

    private String email;

    private Integer status;

    public Integer getAlarmLinkmanId() {
        return alarmLinkmanId;
    }

    public void setAlarmLinkmanId(Integer alarmLinkmanId) {
        this.alarmLinkmanId = alarmLinkmanId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}