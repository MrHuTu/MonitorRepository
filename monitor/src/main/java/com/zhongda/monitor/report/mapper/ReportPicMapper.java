package com.zhongda.monitor.report.mapper;

import java.util.List;

import com.zhongda.monitor.report.model.ReportPic;

public interface ReportPicMapper {
	
	public boolean insertPic(ReportPic reportPic);
	
    public List<ReportPic> selectPicById(String projectId);
}
