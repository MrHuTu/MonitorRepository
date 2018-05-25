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
		return sysCodeMapper.selectscByTypeCode(1);
	}

	@Override
	public List<SysCode> selectProStatus() {
		return sysCodeMapper.selectscByTypeCode(4);
	}

	@Override
	public String[] selectViewDataType() {
		SysCode sysCode = sysCodeMapper.selectViewDataType();
		String itemName = sysCode.getItemName();
		return itemName.split(",");
	}

	@Override
	public List<SysCode> selectscByTypeCode(Integer typeCode) {
		return sysCodeMapper.selectscByTypeCode(typeCode);
	}

	@Override
	public List<SysCode> selectMoniTyTableName() {
		System.out.println(sysCodeMapper.selectMoniTyTableName());
		return sysCodeMapper.selectMoniTyTableName();
	}

}
