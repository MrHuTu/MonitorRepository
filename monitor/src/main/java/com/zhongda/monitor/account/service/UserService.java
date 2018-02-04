package com.zhongda.monitor.account.service;

import java.util.List;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.core.model.Result;

/**
 * Title : 用户管理接口
 * Description : 处理用户的增删改查操作
 * @Author dengzm
 * @Date 2018年1月16日 下午3:42:51
 */
public interface UserService {

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	User selectByUsername(String userName);
	
	/**
	 * 根据Id查用户
	 * @param userId
	 * @return
	 */
	User selectByPrimaryKey(Integer userId);
	
	/**
	 * 根据用户名和ID查用户
	 * @param username
	 * @param userId
	 * @return
	 */
	User selectByPrimaryKeyAndUserName(String userName, Integer userId);

	/**
	 * 根据用户名模糊查询用户
	 * 
	 * @param username
	 * @return
	 */
	List<User> selectAllByUsername(String userName);

	/**
	 * 添加用户
	 * @param user 用户
	 * @return true代表插入成功 ;false代表插入失败
	 */
	boolean insertUser(User user);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<User> selectList(Integer userId);

	/**
	 * 修改用户信息
	 * @param record
	 * @return
	 */
	boolean updateUser(User record);

	/**
	 * 根据用户名删除用户
	 * @param name
	 * @return
	 */
	boolean deleteUser(String userName);

	/**
	 * 根据邮箱号查用户
	 * @param email
	 * @return
	 */
	User selectByEmail(String email);

	/**
	 * 根据手机号码查用户
	 * @param phone
	 * @return
	 */
	User selectByPhone(String phone);

	/**
	 * 根据关键字模糊查询所有字段符合关键字的用户
	 * @param keyword
	 * @param userId
	 * @return
	 */
	List<User> selectUserByKeyword(String keyword, Integer userId);

	/**
	 * 修改用户权限表中用户的权限
	 * @param user
	 * @return
	 */
	int updateUsersRole(User user);

	/**
	 * 查找除管理员和非管理员的所有用户
	 * @return
	 */
	List<User> selectUserWithoutAdmin();

	/**
	 * 返回权限Id
	 * @param userId
	 * @return
	 */
	int selectUserRoleByUserId(Integer userId);
	
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	Result<String> login(String userName, String password);
	
	/**
	 * 根据邮箱修改密码
	 * @param email 邮箱
	 * @param password 用户名
	 * @return
	 */
	boolean updatePassword(String email, String password);
	
	/**
	 * 检查用户是否存在
	 * @param userName 用户名
	 */
	boolean vaildUserName(String userName);
	
	/**
	 * 检查邮箱是否存在
	 * @param email 邮箱
	 */
	boolean validEmail(String email);
	
	/**
	 * 检查手机号码是否存在
	 * @param phone 手机号码
	 */
	boolean validPhone(String phone);
}
