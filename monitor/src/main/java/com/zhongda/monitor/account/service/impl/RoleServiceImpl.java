package com.zhongda.monitor.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.account.mapper.RoleMapper;
import com.zhongda.monitor.account.model.Role;
import com.zhongda.monitor.account.security.SecurityRealm;
import com.zhongda.monitor.account.service.RoleService;
import com.zhongda.monitor.core.model.Result;

/**
 *<p>角色Service实现类</p>
 * @author zmdeng
 * @date 2017年9月25日
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private SecurityRealm securityRealm;

	@Override
	public List<Role> selectRolesByUserId(Integer userId) {
		return roleMapper.selectRolesByUserId(userId);
	}

	@Override
	public List<Role> selectAllRoles() {
		return roleMapper.selectAllRoles();
	}

	@Override
	public List<Role> selectLessRolesByUserId(Integer userId) {
		return roleMapper.selectLessRolesByUserId(userId);
	}

	@Override
	public boolean selectRoleByRoleName(String roleName) {
		Role role = roleMapper.selectRoleByRoleName(roleName);
		if(null == role){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean selectRoleByRoleNameAndRoleId(Integer roleId, String roleName) {
		Role role = roleMapper.selectRoleByRoleName(roleName);
		if(null == role){
			return true;
		}else{
			if(role.getRoleId() == roleId){
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public boolean selectRoleByRoleSign(String roleSign) {
		Role role = roleMapper.selectRoleByRoleSign(roleSign);
		if(null == role){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean selectRoleByRoleSignAndRoleId(Integer roleId, String roleSign) {
		Role role = roleMapper.selectRoleByRoleSign(roleSign);
		if(null == role){
			return true;
		}else{
			if(role.getRoleId() == roleId){
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public Result<Role> insertRole(Role role) {
		int index = roleMapper.insertSelective(role);
		Result<Role> result = new Result<Role>();
		if(index > 0){
			result.setCode(Result.SUCCESS);
			result.setMsg("新增角色成功");
			result.setData(role);
		}else{
			result.setCode(Result.FAILURE);
			result.setMsg("新增角色失败");
		}
		return result;
	}

	@Override
	public Result<Role> updateRole(Role role) {
		int index = roleMapper.updateByPrimaryKeySelective(role);
		Result<Role> result = new Result<Role>();
		if(index > 0){
			result.setCode(Result.SUCCESS);
			result.setMsg("修改角色成功");
			result.setData(role);
		}else{
			result.setCode(Result.FAILURE);
			result.setMsg("修改角色失败");
		}
		return result;
	}

	@Override
	public Result<Role> deleteRole(Integer roleId) {
		int index = roleMapper.deleteByPrimaryKey(roleId);
		Result<Role> result = new Result<Role>();
		if(index > 0){
			result.setCode(Result.SUCCESS);
			result.setMsg("删除角色成功");
		}else{
			result.setCode(Result.FAILURE);
			result.setMsg("删除角色失败");
		}
		return result;
	}

	@Override
	public void clearRoleCache() {
		securityRealm.clearAllCachedAuthorizationInfo();
	}

}
