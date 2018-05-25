package com.zhongda.monitor.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	List<SysCode> selectscByTypeCode(@Param(value = "typeCode") Integer typeCode);

	/**
	 * 代表名
	 * 
	 * @return
	 */
	List<SysCode> selectMoniTyTableName();

	/**
	 * 根据scid查询表名和检测指标
	 * 
	 * @param scId
	 * @return
	 */
	SysCode selectMoniTyTableNameByscid(@Param(value = "scId") Integer scId);

	/**
	 * 查询数据展示类型
	 * 
	 * @return
	 */
	SysCode selectViewDataType();

	/**
	 * 查询字典数据通过TypeCode
	 * 
	 * @param scids
	 * @return
	 */
	List<SysCode> selectSCByTypeCodes(
			@Param(value = "scids") List<Integer> scids);
}