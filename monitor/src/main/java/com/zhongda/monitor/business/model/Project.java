package com.zhongda.monitor.business.model;

import java.util.Date;

public class Project {
    private Integer projectId;

    private String projectName;

    private Integer projectType;

    private String projectAddress;

    private String projectLongitude;

    private String projectLatitude;

    private Date projectBeginTime;

    private Date projectEndTime;

    private Integer projectStatus;

    private String projectDescription;

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

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress == null ? null : projectAddress.trim();
    }

    public String getProjectLongitude() {
        return projectLongitude;
    }

    public void setProjectLongitude(String projectLongitude) {
        this.projectLongitude = projectLongitude == null ? null : projectLongitude.trim();
    }

    public String getProjectLatitude() {
        return projectLatitude;
    }

    public void setProjectLatitude(String projectLatitude) {
        this.projectLatitude = projectLatitude == null ? null : projectLatitude.trim();
    }

    public Date getProjectBeginTime() {
        return projectBeginTime;
    }

    public void setProjectBeginTime(Date projectBeginTime) {
        this.projectBeginTime = projectBeginTime;
    }

    public Date getProjectEndTime() {
        return projectEndTime;
    }

    public void setProjectEndTime(Date projectEndTime) {
        this.projectEndTime = projectEndTime;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription == null ? null : projectDescription.trim();
    }
}