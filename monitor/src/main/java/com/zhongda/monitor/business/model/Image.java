package com.zhongda.monitor.business.model;

public class Image {
    private Integer imageId;

    private Integer userId;

    private Integer projectId;

    private Integer projectType;

    private Integer monitorType;

    private String heatImageUrl;

    private String physicalImageUrl;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
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

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public Integer getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(Integer monitorType) {
        this.monitorType = monitorType;
    }

    public String getHeatImageUrl() {
        return heatImageUrl;
    }

    public void setHeatImageUrl(String heatImageUrl) {
        this.heatImageUrl = heatImageUrl == null ? null : heatImageUrl.trim();
    }

    public String getPhysicalImageUrl() {
        return physicalImageUrl;
    }

    public void setPhysicalImageUrl(String physicalImageUrl) {
        this.physicalImageUrl = physicalImageUrl == null ? null : physicalImageUrl.trim();
    }
}