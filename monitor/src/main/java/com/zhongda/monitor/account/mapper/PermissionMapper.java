package com.zhongda.monitor.account.mapper;

import java.util.List;

import com.zhongda.monitor.account.model.Permission;

public interface PermissionMapper {

	int deleteByPrimaryKey(Integer permissionId);

	int insert(Permission record);

	/**
	 * 插入一个权限
	 * 
	 * @param record
	 *            需插入的权限
	 * @return
	 */
	int insertSelective(Permission record);

	Permission selectByPrimaryKey(Integer permissionId);

	/**
	 * 修改权限信息
	 * 
	 * @param record
	 *            需修改的权限
	 * @return
	 */
	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);

	/**
	 * 根据角色id获取角色所拥有的权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> selectPermissionsByRoleId(Integer roleId);

	/**
	 * 查询所有的权限
	 * 
	 * @return
	 */
	List<Permission> selectAllPermissions();

	/**
	 * 根据权限名称获取权限
	 * 
	 * @param permissionName
	 *            权限名称
	 * @return
	 */
	Permission selectPermissionByPermissionName(String permissionName);

	/**
	 * 根据权限标识获取权限
	 * 
	 * @param permissionSign
	 *            权限标识
	 * @return
	 */
	Permission selectPermissionByPermissionSign(String permissionSign);

	/**
	 * 查询单个权限的详细信息(包括使用该权限的角色信息)
	 * 
	 * @param permissionId
	 *            权限id
	 * @return
	 */
	Permission selectPermissionAndRoleByPermissionId(Integer permissionId);

	/**
	 * 删除一个权限如果有子权限同时删除子权限根据权限id
	 * 
	 * @param permissionId
	 *            需删除权限的id
	 * @return
	 */
	int deletePermissionAndChildPermission(Integer permissionId);

}