package com.zhongda.monitor.account.mapper;

import java.util.List;

import com.zhongda.monitor.account.model.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

	List<Permission> selectPermissionsByRoleId(Integer roleId);

	List<Permission> selectAllPermissions();

	Permission selectPermissionByPermissionName(String permissionName);

	Permission selectPermissionByPermissionSign(String permissionSign);

	int deletePermissionAndChildPermission(Integer permissionId);

	Permission selectPermissionAndRoleByPermissionId(Integer permissionId);
}