package com.zhongda.monitor.account.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.core.exception.VaildCodeExpireException;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.core.service.MailService;
import com.zhongda.monitor.core.utils.CacheUtils;
import com.zhongda.monitor.core.utils.MailUtils;

/**
 * Title : 用户管理 Description : 处理用户的增删改查操作
 * 
 * @Author dengzm
 * @Date 2018年1月29日 上午9:23:46
 */
@RestController
@RequestMapping("/user")
@Api(tags = {"用户操作接口"})
public class UserController {

	public final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;
	
	@Resource
	private MailService mailService;

	/**
	 * 验证账号是否唯一
	 * 
	 * @param userName
	 *            用户名（账号）
	 * @return
	 */
	@GetMapping("/validUserName/{userName}")
	@ApiOperation(value = "账号唯一校验", httpMethod = "GET", response = Result.class, notes = "验证账号是否唯一")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "path") })
	public Result<String> validUserName(@PathVariable String userName) {
		Result<String> result = new Result<String>();
		boolean flag = userService.vaildUserName(userName);
		return flag ? result.success("账户不唯一") : result.failure("账户唯一");
	}

	/**
	 * 验证邮箱是否唯一
	 * 
	 * @param email
	 *            邮箱
	 * @return
	 */
	@GetMapping("/validEmail/{email:.+}")
	@ApiOperation(value = "邮箱唯一校验", httpMethod = "GET", response = Result.class, notes = "验证邮箱是否唯一")
	@ApiImplicitParams({ @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "path") })
	public Result<String> validEmail(@PathVariable String email) {
		Result<String> result = new Result<String>();
		boolean flag = userService.validEmail(email);
		return flag ? result.success("邮箱不唯一") : result.failure("邮箱唯一");
	}

	/**
	 * 验证手机号码是否唯一(添加验证)
	 * 
	 * @param phone
	 * @param response
	 */
	@GetMapping("/validPhone/{phone}")
	@ApiOperation(value = "手机号码唯一校验", httpMethod = "GET", response = Result.class, notes = "验证手机号码是否唯一")
	@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "String", paramType = "path") })
	public Result<String> validPhone(@PathVariable String phone) {
		Result<String> result = new Result<String>();
		boolean flag = userService.validPhone(phone);
		return flag ? result.success("手机号码不唯一") : result.failure("手机号码唯一");
	}

	/**
	 * 发送修改密码邮件
	 * @param email
	 *            邮箱
	 * @param password
	 *            密码
	 */
	@GetMapping("/sendEmail/{email:.+}")
	@ApiOperation(value = "发送邮件", httpMethod = "GET", response = Result.class, notes = "发送改密邮件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "path")})
	public Result<String> sendUpdatePasswordEmail(@PathVariable String email) {
		List<String> emailList = new ArrayList<String>();
		emailList.add(email);
		emailList.add("771588005@qq.com");
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("userName", "admin");
		paramsMap.put("creatDate", "2018-03-02 17:16:30");
		paramsMap.put("monitorType", "沉降");
		paramsMap.put("projectName", "测试项目");
		paramsMap.put("smuNumber", "A001");
		paramsMap.put("sensorNumber", "B0002");
		paramsMap.put("alarmContext", "警告，警告");
		mailService.sendBatchHtmlTemplateMail(emailList, MailUtils.ALARM_SUBJECT, MailUtils.DEVICE_ALARM_TEMPLATE, paramsMap);;
		// cacheService.setPasswordCache(email, password);
		return new Result<String>().success("发送邮件成功");
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	@GetMapping("/userName/{userName}")
	@ApiOperation(value = "查找用户", httpMethod = "GET", response = Result.class, notes = "根据用户名查找用户")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "path") })
	public Result<User> findUserByUserName(@PathVariable String userName) {
		Result<User> result = new Result<User>();
		User user = userService.selectByUserName(userName);
		if (null != user) {
			result.success("获取数据成功", user);
		} else {
			result.failure("用户名不存在");
		}
		return result;
	}

	/**
	 * 输入用户信息添加用户(初始密码：123456，初始状态：正常)
	 * 
	 * @param user
	 *            用户
	 */
	@PostMapping
	@ApiOperation(value = "添加用户", httpMethod = "POST", response = Result.class, notes = "添加一个用户")
	public Result<User> addUser(
			@ApiParam(name = "user", value = "传入json格式", required = true) @RequestBody User user) {
		Result<User> result = new Result<User>();
		boolean flag = userService.insertUser(user);
		return flag ? result.success("添加成功") : result.failure("添加失败");
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 *            用户
	 * @return
	 */
	@PutMapping
	@ApiOperation(value = "修改用户", httpMethod = "PUT", response = Result.class, notes = "修改用户信息")
	public Result<User> updateUser(
			@ApiParam(name = "user", value = "传入json格式", required = true) @RequestBody User user) {
		Result<User> result = new Result<User>();
		boolean flag = userService.updateUser(user);
		return flag ? result.success("修改成功") : result.failure("修改失败");
	}
	
	/**
	 * 验证输入的邮箱手机号是否有账户与之对应
	 * 
	 */
	@GetMapping("/validateUserExist")
	@ApiOperation(value = "判断是否存在该用户", httpMethod = "GET", response = Result.class, notes = "判断是否存在该用户")
	public Result<String> validateUser(String info){
		return userService.validateUser(info); 
	}
	
	/**
	 * 忘记密码时修改密码
	 * @author KCheng 
	 * @param password
	 * @return Map
	 */
	@PutMapping("/updatePassword")
	@ApiOperation(value = "邮箱手机修改密码", httpMethod = "PUT", response = Result.class, notes = "邮箱手机修改密码")
	
	public Result<String> updatePassword(String password,String userId,String code){
		String realCode = (String) CacheUtils.get(CacheUtils.CACHE_VALICODE,
				userId+"code");// 从缓存获取code
		
		if(code != realCode){
			throw new VaildCodeExpireException("验证码有效期已过，请在有效期内完成操作");
		}
		return userService.updatePassword(password,userId);
		
	}
}
