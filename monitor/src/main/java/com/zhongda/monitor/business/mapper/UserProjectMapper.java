package com.zhongda.monitor.business.mapper;

import java.util.List;

import com.zhongda.monitor.business.model.UserProject;

public interface UserProjectMapper {
	int deleteByPrimaryKey(Integer upId);

	int insert(UserProject record);

	int insertSelective(UserProject record);

	UserProject selectByPrimaryKey(Integer upId);

	int updateByPrimaryKeySelective(UserProject record);

	int updateByPrimaryKey(UserProject record);

	/**
	 * 查询所有项目下的用户
	 * 
	 * @return
	 */
	List<UserProject> selectAllPuser();

	/**
	 * 查询所有用户下的项目
	 * 
	 * @return
	 */
	List<UserProject> selectAllUpro();
}