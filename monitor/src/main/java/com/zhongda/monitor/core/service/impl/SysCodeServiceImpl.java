package com.zhongda.monitor.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.core.mapper.SysCodeMapper;
import com.zhongda.monitor.core.model.SysCode;
import com.zhongda.monitor.core.service.SysCodeService;

@Service
public class SysCodeServiceImpl implements SysCodeService {

	@Resource
	private SysCodeMapper sysCodeMapper;

	@Override
	public List<SysCode> selecttypeCode() {
		return sysCodeMapper.selecttypeCode();
	}

	@Override
	public List<SysCode> selectProStatus() {
		return sysCodeMapper.selectProStatus();
	}

}
