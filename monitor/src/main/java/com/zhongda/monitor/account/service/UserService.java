package com.zhongda.monitor.account.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.management.model.PaginationResult;

/**
 * Title : 用户管理接口 Description : 处理用户的增删改查操作
 * 
 * @Author dengzm
 * @Date 2018年1月16日 下午3:42:51
 */
public interface UserService {

	/**
	 * 根据Id查用户
	 * 
	 * @param userId
	 * @return
	 */
	User selectByPrimaryKey(Integer userId);

	/**
	 * 根据用户名获取用户
	 * 
	 * @param username
	 * @return
	 */
	User selectByUserName(String userName);

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            用户
	 * @return true代表插入成功 ;false代表插入失败
	 */
	boolean insertUser(User user);

	/**
	 * 后台管理添加用户
	 * 
	 * @param user
	 * @return
	 */
	boolean addUser(User user);

	/**
	 * 修改用户信息
	 * 
	 * @param record
	 * @return
	 */
	boolean updateUser(User record);

	/**
	 * 用户登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	Result<String> login(String userName, String password);

	/**
	 * 检查用户是否存在
	 * 
	 * @param userName
	 *            用户名
	 */
	boolean vaildUserName(String userName);

	/**
	 * 检查邮箱是否存在
	 * 
	 * @param email
	 *            邮箱
	 */
	boolean validEmail(String email);

	/**
	 * 检查手机号码是否存在
	 * 
	 * @param phone
	 *            手机号码
	 */
	boolean validPhone(String phone);

	/**
	 * 根据邮箱 手机修改密码
	 * 
	 */
	Result<String> updatePassword(String password, String userId);

	/**
	 * 验证输入的邮箱手机是否存在用户
	 */
	Result<String> validateUser(String info);

	/**
	 * 查询所有user
	 * 
	 * @return
	 */
	List<User> selectAll();

	/**
	 * 分页查询所有user
	 * 
	 * @return
	 */
	PaginationResult selectAllUser(int offset, int limit, String condition);

	/**
	 * 查询项目下的所有用户
	 * 
	 * @return
	 */
	List<User> selectPuser(@Param(value = "projectId") Integer projectId);

	/**
	 * 删除对应id的用户们
	 * 
	 * @param deleteIds
	 * @return
	 */
	boolean deleteUsers(Integer userId);

}
