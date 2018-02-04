package com.zhongda.monitor.account.service;

import java.util.List;

import com.zhongda.monitor.account.model.Role;
import com.zhongda.monitor.core.model.Result;

/**
 *<p>角色 业务 接口</p>
 * @author zmdeng
 * @date 2017年9月25日
 */
public interface RoleService {

	/**
	 * 根据用户id获取用户所拥有的角色
	 * @param userId
	 * @return
	 */
	List<Role> selectRolesByUserId(Integer userId);

	/**
     * 查role表中所有
     * @param 
     * @return
     */
    List<Role> selectAllRoles();
    
    /**
     * 根据用户id获取小于该用户所拥有的角色
     * @param userId
     * @return
     */
	List<Role> selectLessRolesByUserId(Integer userId);
	
	/**
	 * 判断角色名称在数据库中是否存在
	 * @param roleName 角色名称
	 * @return true代表不存在；false代表存在
	 */
	boolean selectRoleByRoleName(String roleName);
	
	/**
	 * 判断角色名称在数据库中是否存在,如果存在角色id是否等于传过来的角色id
	 * @param roleId 角色id
	 * @param roleName 角色名称
	 * @return true代表不存在；false代表存在
	 */
	boolean selectRoleByRoleNameAndRoleId(Integer roleId, String roleName);
	
	/**
	 * 判断角色标识在数据库中是否存在
	 * @param roleSign 角色标识
	 * @return true代表不存在；false代表存在
	 */
	boolean selectRoleByRoleSign(String roleSign);
	
	/**
	 * 判断角色标识在数据库中是否存在,如果存在角色id是否等于传过来的角色id
	 * @param roleId 角色id
	 * @param roleSign 角色标识
	 * @return true代表不存在；false代表存在
	 */
	boolean selectRoleByRoleSignAndRoleId(Integer roleId, String roleSign);
	
	/**
	 * 新增一个角色，保存进数据库
	 * @param role 需保存的角色
	 * @return 返回插入结果信息
	 */
	Result<Role> insertRole(Role role);
	
	/**
	 * 修改角色信息
	 * @param role 需修改的角色
	 * @return 返回插入结果信息
	 */
	Result<Role> updateRole(Role role);
	
	/**
	 * 删除角色
	 * @param roleId 需删除的角色的id
	 * @return 返回插入结果信息
	 */
	Result<Role> deleteRole(Integer roleId);
	
	/**
	 * 清除角色的缓存
	 */
	void clearRoleCache();

}
