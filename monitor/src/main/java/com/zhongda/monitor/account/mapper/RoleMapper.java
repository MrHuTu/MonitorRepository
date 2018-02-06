package com.zhongda.monitor.account.mapper;

import java.util.List;

import com.zhongda.monitor.account.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<Role> selectRolesByUserId(Integer userId);

	List<Role> selectAllRoles();

	List<Role> selectLessRolesByUserId(Integer userId);

	Role selectRoleByRoleName(String roleName);

	Role selectRoleByRoleSign(String roleSign);
}