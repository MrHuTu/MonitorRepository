package com.zhongda.monitor.account.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.account.mapper.UserMapper;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.core.model.Result;

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
	
	@Resource
	private ObjectMapper objectMapper;
	
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
	public boolean updateUser(User user) {
		int index = userMapper.updateByPrimaryKeySelective(user);
		return index > 0 ? true : false;
	}

	@Override
	public User selectByPrimaryKey(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public Result<String> login(String userName, String password) {
		Result<String> result = new Result<String>();
		// 判断用户是否已经登录
		if (ShiroUtils.isLogin()) {
			result.failure("该用户已经登录");
		}else{
			// 通过数据库进行验证
			final User user = userMapper.selectByUserName(userName);
			if (user == null) {
	            throw new UnknownAccountException("该帐号不存在！");
	        }else if("禁用".equals(user.getStatus())){
	        	throw new DisabledAccountException("该账户已被禁用 ，请联系管理员！");
	        }
			//验证密码是否正确
			password = ShiroUtils.encryptPassword(password, userName);
			if(password.equals(user.getPassword())){
				try {
					user.setPassword(null);
					result.success("登录成功", objectMapper.writeValueAsString(user));
				} catch (JsonProcessingException e) {
					throw new RuntimeException("用户信息异常，无法转换成json字符串，请联系管理员 -> " + e.getMessage());
				}
			} else {
				throw new IncorrectCredentialsException("用户名或密码错误 ！");
			}
		}
		return result;
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

	@Override
	public User selectByUserName(String userName) {
		return userMapper.selectByUserName(userName);
	}
}