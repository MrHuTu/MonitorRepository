package com.zhongda.monitor.account.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.exception.ForbiddenException;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.service.TokenService;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.account.utils.TokenUtils;
import com.zhongda.monitor.business.task.PushSensorData;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.core.utils.StringUtils;

/**
 * Title: Token管理 Description: 处理用户的登录、登出操作
 * 
 * @Author dengzm
 * @Date 2018年1月11日 下午4:36:29
 */
@RestController
@RequestMapping("/token")
@Api(tags = { "Token操作接口" })
public class TokenController {

	private static final Logger logger = LoggerFactory
			.getLogger(TokenController.class);

	@Resource
	private UserService userService;

	@Resource
	private TokenService tokenService;

	/**
	 * 登录处理
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            value
	 */
	@PostMapping("/login")
	@ApiOperation(value = "登录--zemin.deng", httpMethod = "POST", response = Result.class, notes = "根据用户名和密码登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form") })
	public Result<String> login(String userName, String password) {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			throw new ForbiddenException("用户名或密码不可为空！");
		}
		Result<String> result = userService.login(userName, password);

		if (result.getCode() == Result.SUCCESS) {
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("userId", Integer.parseInt(result.getData()));
			claims.put("userName", userName);
			// 创建token
			String token = tokenService.createToken(claims,
					ShiroUtils.encryptPassword(password, userName));
			logger.debug(userName + " 用户登录生成的Token: " + token);
			result.setData(token);

			// 放入推送消息的集合
			HashSet<String> tokenSet = PushSensorData.concurrentHashMap
					.get(userName);
			if (null == tokenSet) {
				tokenSet = new HashSet<String>();
			}
			tokenSet.add(token);
			PushSensorData.concurrentHashMap.put(userName, tokenSet);
			// 放入推送消息的集合
			System.out.println(userName + " size:" + tokenSet.size());
			System.out.println("mapSize:"
					+ PushSensorData.concurrentHashMap.size());
		} else {
			result.setMsg("登录失败," + result.getMsg());
		}
		return result;
	}

	/**
	 * 注销处理
	 * 
	 * @param request
	 * @return
	 */
	@DeleteMapping("/logout")
	@ApiOperation(value = "注销--zemin.deng", httpMethod = "DELETE", response = Result.class, notes = "注销")
	public Result<String> logout() {

		// 在推送消息列中删除注销用户
		String token = TokenUtils.getToken();
		User user = ShiroUtils.getUserFromToken(token);
		HashSet<String> tokenSet = PushSensorData.concurrentHashMap.get(user
				.getUserName());
		boolean isSuccess = tokenSet.remove(token);
		if (isSuccess) {
			if (tokenSet.size() == 0) {
				PushSensorData.concurrentHashMap.remove(user.getUserName());
			}
			// 登出操作
			ShiroUtils.logout();
			return new Result<String>().success("注销成功");
		}
		return new Result<String>().success("注销失败");

	}
}
