package com.zhongda.monitor.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.mapper.ProjectParaMapper;
import com.zhongda.monitor.report.model.fictitious.ProjectPara;
import com.zhongda.monitor.report.service.ProjectParaService;
@Service
public class ProjectParaServiceImpl implements ProjectParaService {
	@Autowired
	ProjectParaMapper projectParaMapper;
	@Override
	public List<ProjectPara> selectProjectPara() {
		
		return projectParaMapper.selectProjectPara();
		
	}

}
