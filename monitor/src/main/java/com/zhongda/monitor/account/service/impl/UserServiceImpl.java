package com.zhongda.monitor.account.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.account.mapper.UserMapper;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.core.utils.ShiroUtils;

/**
 * Title : 用户管理实现类
 * Description : 处理用户的增删改查操作
 * @Author dengzm
 * @Date 2018年1月16日 下午3:45:14
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;	
	
	@Override
	public boolean insertUser(User user) {
		String cryptedPwd = new Md5Hash("123456", user.getUserName(),
				1024).toString();
		user.setPassword(cryptedPwd);
		user.setStatus("正常");
		user.setCreateTime(new Date());
		int index = userMapper.insert(user);
		return index > 0 ? true : false;
	}

	@Override
	public List<User> selectList(Integer userId) {
		return userMapper.selectAllUser(userId);
	}
	
	@Override
	public List<User> selectAllByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectByUsername(String userName) {
		return userMapper.selectByUsername(userName);
	}

	@Override
	public boolean deleteUser(String userName) {
		return userMapper.deleteUser(userName);
	}

	@Override
	public boolean updateUser(User user) {
		int index = userMapper.updateByPrimaryKeySelective(user);
		return index > 0 ? true : false;
	}

	@Override
	public User selectByPrimaryKey(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public User selectByEmail(String email) {
		return userMapper.selectByEmail(email);
	}

	@Override
	public User selectByPhone(String phone) {
		return userMapper.selectByPhone(phone);
	}

	@Override
	public List<User> selectUserByKeyword(String keyword, Integer userId) {
		return userMapper.selectUserByKeyword(keyword, userId);
	}

	@Override
	public int updateUsersRole(User user) {
		return userMapper.updateUsersRole(user);
	}

	@Override
	public List<User> selectUserWithoutAdmin() {
		return userMapper.selectUserWithoutAdmin();
	}

	@Override
	public int selectUserRoleByUserId(Integer userId) {
		return userMapper.selectUserRoleByUserId(userId);
	}

	@Override
	public User selectByPrimaryKeyAndUserName(String userName, Integer userId) {
		return userMapper.selectByPrimaryKeyAndUserName(userName, userId);
	}

	@Override
	public Result<String> login(String userName, String password) {
		Result<String> result = new Result<String>();
		// 判断用户是否已经登录
		if (ShiroUtils.isLogin()) {
			result.setCode(Result.FAILURE);
			result.setMsg("该用户已经登录");
		}else{
			UsernamePasswordToken shiroToken = new UsernamePasswordToken(userName, password);
			// 身份验证
			ShiroUtils.login(shiroToken);
			result.setCode(Result.SUCCESS);
		}
		return result;
	}

	@Override
	public boolean updatePassword(String email, String password) {
		String encryptPassword = ShiroUtils.encryptPassword(password, ShiroUtils.getUser().getUserName());
		int index = userMapper.updatePasswordByEmail(email, encryptPassword);
		return index > 0 ? true : false;
	}

	@Override
	public boolean vaildUserName(String userName) {
		return false;
	}

	@Override
	public boolean validEmail(String email) {
		return false;
	}

	@Override
	public boolean validPhone(String phone) {
		return false;
	}
}