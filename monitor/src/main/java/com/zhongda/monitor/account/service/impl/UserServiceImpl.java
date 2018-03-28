package com.zhongda.monitor.account.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.account.mapper.UserMapper;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.core.annotation.SysLogAnnotation;
import com.zhongda.monitor.core.exception.VaildCodeExpireException;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.core.utils.CacheUtils;

/**
 * Title : 用户管理实现类 Description : 处理用户的增删改查操作
 * 
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
		String cryptedPwd = new Md5Hash("123456", user.getUserName(), 1024)
				.toString();
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
	@SysLogAnnotation("用户名为${0}的用户登录")
	public Result<String> login(String userName, String password) {
		Result<String> result = new Result<String>();
		// 通过数据库进行验证
		final User user = userMapper.selectByUserName(userName);
		if (user == null) {
			throw new UnknownAccountException("该帐号不存在！");
		} else if ("禁用".equals(user.getStatus())) {
			throw new DisabledAccountException("该账户已被禁用 ，请联系管理员！");
		}
		// 验证密码是否正确
		password = ShiroUtils.encryptPassword(password, userName);
		if (password.equals(user.getPassword())) {
			result.success("登录成功", user.getUserId().toString());
		} else {
			throw new IncorrectCredentialsException("用户名或密码错误 ！");
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

	public Result<String> updatePassword( String password,String userId) {
		Result<String> result = new Result<String>();
		// 获取用户信息
		User user = (User) CacheUtils.get(CacheUtils.CACHE_USER, userId);
		if(null == user){
			throw new VaildCodeExpireException("页面有效期为30分钟，您已超过有效期，请刷新重试！");
		}
		String passwordCode = ShiroUtils.encryptPassword(password,
				user.getUserName());
		user.setPassword(passwordCode);
		int resultNum = userMapper.updateByPrimaryKeySelective(user);
		
		if (resultNum == 0) {
			return result.failure("修改失败");
		}
		return result.success("修改成功");
	}

	public Result<String> validateUser(String info) {
		Result<String> result = new Result<String>();
		User user = new User();
		if (info.indexOf("@") != -1) {
			user = userMapper.validateUserByEmail(info);
		} else {
			user = userMapper.validateUserByPhone(info);
		}
		// 将user放进缓存
		if (null == user) {
			return result.failure("该用户不存在");
		}
		CacheUtils.put(CacheUtils.CACHE_USER, user.getUserId()+"", user);
		return result.success("用户存在", user.getUserId()+"");
	}

}