package com.zhongda.monitor.business.model;

public class TerminalsProject {
    private Integer tpId;

    private String smuId;

    private Integer projectId;

    public Integer getTpId() {
        return tpId;
    }

    public void setTpId(Integer tpId) {
        this.tpId = tpId;
    }

    public String getSmuId() {
        return smuId;
    }

    public void setSmuId(String smuId) {
        this.smuId = smuId == null ? null : smuId.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}