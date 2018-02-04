package com.zhongda.monitor.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.zhongda.monitor.account.mapper.PermissionMapper;
import com.zhongda.monitor.account.model.Permission;
import com.zhongda.monitor.account.service.PermissionService;
import com.zhongda.monitor.core.model.Result;

/**
 *<p>权限 业务 实现类</p>
 * @author zmdeng
 * @date 2017年9月25日
 */
@Service
public class PermissionServiceImpl implements PermissionService{

	@Resource
	private PermissionMapper permissionMapper;

	@Override
	public List<Permission> selectPermissionsByRoleId(Integer roleId) {
		return permissionMapper.selectPermissionsByRoleId(roleId);
	}

	@Override
	public List<Permission> selectAllPermissions() {
		return permissionMapper.selectAllPermissions();
	}

	@Override
	public Map<String, Object> selectAllPermissionsOnGrade() {
		Map<String, Object> permissionMap = new HashMap<String, Object>();
		List<Permission> permissionList = permissionMapper.selectAllPermissions();
		//存放所有权限
		permissionMap.put("permissionList", permissionList);
		Multimap<Integer,Permission> permissionMultimap = ArrayListMultimap.create();
		for (Permission permission : permissionList) {
			permissionMultimap.put(permission.getPermissionParentId(), permission);
		}
		//存放按等级分类的所有权限
		permissionMap.put("permissionListOnGrade", permissionMultimap.asMap());
		return permissionMap;
	}
	
	@Override
	public Map<String, Object> selectPermissionListAndOwn(Integer roleId) {
		Map<String, Object> permissionMap = new HashMap<String, Object>();
		List<Permission> permissionList = permissionMapper.selectAllPermissions();
		List<Permission> permissionOwnList = permissionMapper.selectPermissionsByRoleId(roleId);
		//存放当前角色所拥有的权限
		permissionMap.put("permissionOwnList", permissionOwnList);
		Multimap<Integer,Permission> permissionMultimap = ArrayListMultimap.create();
		for (Permission permission : permissionList) {
			permissionMultimap.put(permission.getPermissionParentId(), permission);
		}
		//存放按等级分类的所有权限
		permissionMap.put("permissionListOnGrade", permissionMultimap.asMap());
		return permissionMap;
	}

	
	@Override
	public boolean selectPermissionByPermissionName(String permissionName) {
		Permission permission = permissionMapper.selectPermissionByPermissionName(permissionName);
		if(null == permission){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean selectPermissionByPermissionNameAndPermissionId(Integer permissionId, String permissionName) {
		Permission permission = permissionMapper.selectPermissionByPermissionName(permissionName);
		if(null == permission){
			return true;
		}else{
			if(permission.getPermissionId() == permissionId){
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public boolean selectPermissionByPermissionSign(String permissionSign) {
		Permission permission = permissionMapper.selectPermissionByPermissionSign(permissionSign);
		if(null == permission){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean selectPermissionByPermissionSignAndPermissionId(Integer permissionId, String permissionSign) {
		Permission permission = permissionMapper.selectPermissionByPermissionSign(permissionSign);
		if(null == permission){
			return true;
		}else{
			if(permission.getPermissionId() == permissionId){
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public Result<Permission> insertPermission(Permission permission) {
		int index = permissionMapper.insertSelective(permission);
		Result<Permission> result = new Result<Permission>();
		if(index > 0){
			result.setCode(Result.SUCCESS);
			result.setMsg("新增权限成功");
			result.setData(permission);
		}else{
			result.setCode(Result.FAILURE);
			result.setMsg("新增权限失败");
		}
		return result;
	}

	@Override
	public Result<Permission> updatePermission(Permission permission) {
		int index = permissionMapper.updateByPrimaryKeySelective(permission);
		Result<Permission> result = new Result<Permission>();
		if(index > 0){
			result.setCode(Result.SUCCESS);
			result.setMsg("修改权限成功");
			result.setData(permission);
		}else{
			result.setCode(Result.FAILURE);
			result.setMsg("修改权限失败");
		}
		return result;
	}

	@Override
	public Result<Permission> deletePermission(Integer permissionId) {
		int index = permissionMapper.deletePermissionAndChildPermission(permissionId);
		Result<Permission> result = new Result<Permission>();
		if(index > 0){
			result.setCode(Result.SUCCESS);
			result.setMsg("删除权限成功");
		}else{
			result.setCode(Result.FAILURE);
			result.setMsg("删除权限失败");
		}
		return result;
	}

	@Override
	public Permission selectPermissionAndRoleByPermissionId(Integer permissionId) {
		return permissionMapper.selectPermissionAndRoleByPermissionId(permissionId);
	}

}
