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

	/**
	 * 查询项目通过code
	 * 
	 * @return
	 */
	List<SysCode> selectscByTypeCode(Integer typeCode);

	/**
	 * 查询数据展示类型
	 * 
	 * @return
	 */
	String[] selectViewDataType();

	/**
	 * 代表名
	 * 
	 * @return
	 */
	List<SysCode> selectMoniTyTableName();

}
