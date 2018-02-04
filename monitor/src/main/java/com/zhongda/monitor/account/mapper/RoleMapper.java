package com.zhongda.monitor.account.mapper;

import java.util.List;

import com.zhongda.monitor.account.model.Role;

public interface RoleMapper {

	/**
	 * 删除一个角色根据角色id
	 * 
	 * @param roleId
	 *            需删除角色的id
	 * @return
	 */
	int deleteByPrimaryKey(Integer roleId);

	int insert(Role record);

	/**
	 * 插入一个角色
	 * 
	 * @param record
	 *            需插入的角色
	 * @return
	 */
	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer roleId);

	/**
	 * 修改角色信息
	 * 
	 * @param record
	 *            需修改的角色
	 * @return
	 */
	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	/**
	 * 根据用户id获取用户所拥有的角色
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> selectRolesByUserId(Integer userId);

	/**
	 * 查role表中所有
	 * 
	 * @param
	 * @return
	 */
	List<Role> selectAllRoles();

	/**
	 * 根据用户id获取小于该用户所拥有的角色
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> selectLessRolesByUserId(Integer userId);

	/**
	 * 根据角色名称获取角色
	 * 
	 * @param roleName
	 *            角色名称
	 * @return
	 */
	Role selectRoleByRoleName(String roleName);

	/**
	 * 根据角色标识获取角色
	 * 
	 * @param roleSign
	 *            角色标识
	 * @return
	 */
	Role selectRoleByRoleSign(String roleSign);
}