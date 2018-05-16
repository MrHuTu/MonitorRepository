package com.zhongda.monitor.core.service;

import java.util.List;

import com.zhongda.monitor.core.model.SysCode;

public interface SysCodeService {

	/**
	 * 查询项目类型
	 * 
	 * @return
	 */
	List<SysCode> selecttypeCode();

	/**
	 * 查询项目状态
	 * 
	 * @return
	 */
	List<SysCode> selectProStatus();

}
