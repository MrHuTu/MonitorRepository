package com.zhongda.monitor.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.zhongda.monitor.account.model.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userId);

	// 修改用户信息
	@CacheEvict(value = "userCache", allEntries = true)
	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	@Cacheable(cacheNames = "userCache")
	User selectByUserName(String userName);

	/**
	 * 验证输入的邮箱手机号是否有账户与之对应
	 * 
	 */
	public User validateUserByPhone(@Param("phone") String phone);

	/**
	 * 验证输入的邮箱是否有账户与之对应
	 * 
	 * @param email
	 * @return
	 */
	public User validateUserByEmail(@Param("email") String email);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<User> selectAll();

	/**
	 * 查询项目下的所有用户
	 * 
	 * @return
	 */
	List<User> selectPuser(@Param(value = "projectId") Integer projectId);

	/**
	 * 后台管理，根据搜索框条件查询用户
	 * 
	 * @param condition
	 * @return
	 */
	List<User> seelctSearchUserByManege(
			@Param(value = "condition") String condition);

	int deleteUsers(List<Integer> userIds);
	/**
	 * 查询项目下用户--by：kx
	 * @param projectId
	 * @return
	 */
	List<User> selectUsersByProjectId(Integer projectId);
	/**
	 * 查询项目下未拥有用户
	 * @param projectId
	 * @return
	 */
	List<User> selectNoUsersByProjectId(Integer projectId);
	/**
	 * 带条件查询项目下所有用户
	 * @param condition
	 * @param projectId
	 * @return
	 */
	List<User> selectUsersByProjectIdCondition(@Param(value="condition")String condition,
			@Param(value="projectId")Integer projectId);
	/**
	 * 带项目查询项目下所有未拥有用户
	 * @param condition
	 * @param projectId
	 * @return
	 */
	List<User> selectNoUsersByProjectIdCondition(@Param(value="condition")String condition,
			@Param(value="projectId")Integer projectId);
}