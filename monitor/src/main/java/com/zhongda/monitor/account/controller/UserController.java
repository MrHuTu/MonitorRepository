package com.zhongda.monitor.account.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.annotation.IgnoreSecurity;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.core.model.Result;

/**
 * Title : 用户管理 Description : 处理用户的增删改查操作
 * 
 * @Author dengzm
 * @Date 2018年1月29日 上午9:23:46
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户模块", tags = { "用户操作接口" })
public class UserController {

	public final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;

	/**
	 * 验证账号是否唯一
	 * 
	 * @param userName
	 *            用户名（账号）
	 * @return
	 */
	@RequestMapping(value = "/validUserName", method = RequestMethod.GET)
	@ApiOperation(value = "账号唯一校验", httpMethod = "GET", response = Result.class, notes = "验证账号是否唯一")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query") })
	public Result<String> validUserName(String userName) {
		Result<String> result = new Result<String>();
		boolean flag = userService.vaildUserName(userName);
		return flag ? result.setCode(Result.FAILURE).setMsg("账户不唯一") : result
				.setCode(Result.FAILURE).setMsg("账户唯一");
	}

	/**
	 * 验证邮箱是否唯一
	 * 
	 * @param email
	 *            邮箱
	 * @return
	 */
	@RequestMapping(value = "/validEmail", method = RequestMethod.GET)
	@ApiOperation(value = "邮箱唯一校验", httpMethod = "GET", response = Result.class, notes = "验证邮箱是否唯一")
	@ApiImplicitParams({ @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "query") })
	public Result<String> validEmail(String email) {
		Result<String> result = new Result<String>();
		boolean flag = userService.validEmail(email);
		return flag ? result.setCode(Result.FAILURE).setMsg("邮箱不唯一") : result
				.setCode(Result.FAILURE).setMsg("邮箱唯一");
	}

	/**
	 * 验证手机号码是否唯一(添加验证)
	 * 
	 * @param phone
	 * @param response
	 */
	@RequestMapping(value = "/validPhone", method = RequestMethod.GET)
	@ApiOperation(value = "手机号码唯一校验", httpMethod = "GET", response = Result.class, notes = "验证手机号码是否唯一")
	@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "String", paramType = "query") })
	public Result<String> validPhone(String phone) {
		Result<String> result = new Result<String>();
		boolean flag = userService.validPhone(phone);
		return flag ? result.setCode(Result.FAILURE).setMsg("手机号码不唯一") : result
				.setCode(Result.FAILURE).setMsg("手机号码唯一");
	}

	/**
	 * 发送修改密码邮件
	 * 
	 * @param email
	 *            邮箱
	 * @param password
	 *            密码
	 */
	@RequestMapping(value = "/sendUpdatePasswordEmail", method = RequestMethod.GET)
	@ApiOperation(value = "发送改密邮件", httpMethod = "GET", response = Result.class, notes = "发送改密邮件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form") })
	public Result<String> sendUpdatePasswordEmail(String email, String password) {
		// emailService.sendUpdatePasswordEmail(email);
		// cacheService.setPasswordCache(email, password);
		return new Result<String>().setCode(Result.SUCCESS).setMsg("发送邮件成功");
	}

	/**
	 * 根据邮箱修改密码
	 * 
	 * @param email
	 *            邮箱
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
	@ApiOperation(value = "修改密码", httpMethod = "PUT", response = Result.class, notes = "根据邮箱修改密码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "query") })
	public Result<String> updatePassword(String email) {
		Result<String> result = new Result<String>();
		/*
		 * String password = cacheService.getPasswordCache(email); if (null !=
		 * email && null != password) { userService.updatePassword(email,
		 * password); result.setCode(Result.SUCCESS).setMsg("修改密码成功"); } else {
		 * result.setCode(Result.FAILURE).setMsg("修改密码失败"); }
		 */
		return result;
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	@IgnoreSecurity
	@RequestMapping(value = "/findUserByUserName", method = RequestMethod.GET)
	@ApiOperation(value = "查找用户", httpMethod = "GET", response = Result.class, notes = "根据用户名查找用户")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query") })
	public Result<User> findUserByUserName(String userName) {
		Result<User> result = new Result<User>();
		User user = userService.selectByUserName(userName);
		if (null != user) {
			result.setCode(Result.SUCCESS).setMsg("获取数据成功").setData(user);
		} else {
			result.setCode(Result.FAILURE).setMsg("用户名不存在");
		}
		return result;
	}

	/**
	 * 输入用户信息添加用户(初始密码：123456，初始状态：正常)
	 * 
	 * @param user
	 *            用户
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ApiOperation(value = "添加用户", httpMethod = "POST", response = Result.class, notes = "添加一个用户")
	public Result<User> addUser(
			@ApiParam(name = "user", value = "传入json格式", required = true) @RequestBody User user) {
		Result<User> result = new Result<User>();
		boolean flag = userService.insertUser(user);
		return flag ? result.setCode(Result.SUCCESS).setMsg("添加成功") : result
				.setCode(Result.FAILURE).setMsg("添加失败");
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 *            用户
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	@ApiOperation(value = "修改用户", httpMethod = "PUT", response = Result.class, notes = "修改用户信息")
	public Result<User> updateUser(
			@ApiParam(name = "user", value = "传入json格式", required = true) @RequestBody User user) {
		Result<User> result = new Result<User>();
		boolean flag = userService.updateUser(user);
		return flag ? result.setCode(Result.SUCCESS).setMsg("修改成功") : result
				.setCode(Result.FAILURE).setMsg("修改失败");
	}

}
