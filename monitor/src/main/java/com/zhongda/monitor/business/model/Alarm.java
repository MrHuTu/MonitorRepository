package com.zhongda.monitor.business.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Alarm {
    private Integer alarmId;

    private Integer userId;

    private String userName;

    private Integer projectId;

    private String projectName;

    private Integer detectionId;

    private String smuCmsId;
    
    private String smuCmsChannel;

    private String sensorId;

    private Integer alarmTypeId;

    private String alarmContext;

    private Date createTime;

    private Integer status;

    private Integer alarmLevel;

    private Integer frequency;
    
    //存放测点名称
    private String detectionName;
    
	//临时存放告警类型名称
    private String alarmType;
    
    //临时存放检测类型名称
    private String detectionType;
    
    //临时存放告警状态
    private String alarmStatus;
    
    //数据库不存在该字段，只作为分页时存储当前页数据时使用
    private Integer pageNum;
    
    //数据库不存在该字段，只作为分页时存储每页记录条数数据时使用
    private Integer pageSize;
    
    //数据库不存在该字段，只作为查询时判断查询条件创建时间是否在该范围内时使用
    private Date startCreateTime;
    
    //数据库不存在该字段，只作为查询时判断查询条件创建时间是否在该范围内时使用
    private Date endCreateTime;
    
    private List<AlarmLinkman> alarmLinkmans;
    
    public String getDetectionName() {
		return detectionName;
	}

	public void setDetectionName(String detectionName) {
		this.detectionName = detectionName;
	}

    
    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getDetectionId() {
        return detectionId;
    }

    public void setDetectionId(Integer detectionId) {
        this.detectionId = detectionId;
    }

    public String getSmuCmsId() {
        return smuCmsId;
    }

    public void setSmuCmsId(String smuCmsId) {
        this.smuCmsId = smuCmsId == null ? null : smuCmsId.trim();
    }

    public String getSmuCmsChannel() {
		return smuCmsChannel;
	}

	public void setSmuCmsChannel(String smuCmsChannel) {
		this.smuCmsChannel = smuCmsChannel == null ? null : smuCmsChannel.trim();
	}

	public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId == null ? null : sensorId.trim();
    }

    public Integer getAlarmTypeId() {
        return alarmTypeId;
    }

    public void setAlarmTypeId(Integer alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }

    public String getAlarmContext() {
        return alarmContext;
    }

    public void setAlarmContext(String alarmContext) {
        this.alarmContext = alarmContext == null ? null : alarmContext.trim();
    }
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Integer alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType == null ? null : alarmType.trim();
	}	
	
	public String getDetectionType() {
		return detectionType;
	}

	public void setDetectionType(String detectionType) {
		this.detectionType = detectionType == null ? null : detectionType.trim();
	}

	public String getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus == null ? null : alarmStatus.trim();
	}
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = startCreateTime;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public List<AlarmLinkman> getAlarmLinkmans() {
		return alarmLinkmans;
	}

	public void setAlarmLinkmans(List<AlarmLinkman> alarmLinkmans) {
		this.alarmLinkmans = alarmLinkmans;
	}
}