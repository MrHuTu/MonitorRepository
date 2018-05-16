package com.zhongda.monitor.core.mapper;

import java.util.List;

import com.zhongda.monitor.core.model.SysCode;

public interface SysCodeMapper {
	int deleteByPrimaryKey(Integer scId);

	int insert(SysCode record);

	int insertSelective(SysCode record);

	SysCode selectByPrimaryKey(Integer scId);

	int updateByPrimaryKeySelective(SysCode record);

	int updateByPrimaryKey(SysCode record);

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