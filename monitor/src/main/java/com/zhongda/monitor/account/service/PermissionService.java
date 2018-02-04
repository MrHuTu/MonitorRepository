package com.zhongda.monitor.account.service;

import java.util.List;
import java.util.Map;

import com.zhongda.monitor.account.model.Permission;
import com.zhongda.monitor.core.model.Result;

/**
 *<p>权限 业务 接口</p>
 * @author zmdeng
 * @date 2017年9月25日
 */
public interface PermissionService {

	/**
	 * 根据角色id获取角色所拥有的权限
	 * @param roleId
	 * @return
	 */
	List<Permission> selectPermissionsByRoleId(Integer roleId);
	
	/**
	 * 查询所有的权限
	 * @return
	 */
	List<Permission> selectAllPermissions();
	
	/**
	 * 查询所有的权限并且按照父子关系归类
	 * @return
	 */
	Map<String, Object> selectAllPermissionsOnGrade();
	
	/**
	 * 查询所有的权限并且按照父子关系归类并获取当前角色所拥有的权限
	 * @param roleId 当前角色Id
	 * @return
	 */
	Map<String, Object> selectPermissionListAndOwn(Integer roleId);
	
	/**
	 * 判断权限名称在数据库中是否存在
	 * @param permissionName 权限名称
	 * @return true代表不存在；false代表存在
	 */
	boolean selectPermissionByPermissionName(String permissionName);
	
	/**
	 * 判断权限标识在数据库中是否存在
	 * @param permissionSign 权限标识
	 * @return true代表不存在；false代表存在
	 */
	boolean selectPermissionByPermissionSign(String permissionSign);
	
	/**
	 * 判断权限名称在数据库中是否存在,如果存在权限id是否等于传过来的权限id
	 * @param permissionId 权限id
	 * @param permissionName 权限名称
	 * @return true代表不存在；false代表存在
	 */
	boolean selectPermissionByPermissionNameAndPermissionId(
			Integer permissionId, String permissionName);
	
	/**
	 * 判断权限标识在数据库中是否存在,如果存在权限id是否等于传过来的权限id
	 * @param permissionId 权限id
	 * @param permissionSign 权限标识
	 * @return true代表不存在；false代表存在
	 */
	boolean selectPermissionByPermissionSignAndPermissionId(
			Integer permissionId, String permissionSign);
	
	/**
	 * 新增一个权限，保存进数据库
	 * @param permission 需保存的权限
	 * @return 返回插入结果信息
	 */
	Result<Permission> insertPermission(Permission permission);
	
	/**
	 * 修改权限信息
	 * @param permission 需修改的权限
	 * @return 返回插入结果信息
	 */
	Result<Permission> updatePermission(Permission permission);
	
	/**
	 * 删除一个权限如果有子权限同时删除子权限
	 * @param permissionId 需删除的权限的id
	 * @return 返回插入结果信息
	 */
	Result<Permission> deletePermission(Integer permissionId);
	
	/**
	 * 查询单个权限的详细信息(包括使用该权限的角色信息)
	 * @param permissionId 权限id
	 * @return
	 */
	Permission selectPermissionAndRoleByPermissionId(Integer permissionId);

}
