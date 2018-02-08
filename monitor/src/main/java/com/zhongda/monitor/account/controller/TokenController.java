package com.zhongda.monitor.account.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.account.annotation.IgnoreSecurity;
import com.zhongda.monitor.account.security.StatelessToken;
import com.zhongda.monitor.account.service.TokenService;
import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.core.utils.ShiroUtils;

/**
 * Title: Token管理    
 * Description: 处理用户的登录、登出操作
 * @Author dengzm
 * @Date 2018年1月11日 下午4:36:29
 */
@RestController
@RequestMapping("/token")
@Api(tags = {"Token操作接口"})
public class TokenController {

	private static final Logger logger = Logger.getLogger(TokenController.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private TokenService tokenService;
	  
	/**
	 *  登录处理
	 * @param userName 用户名
	 * @param password value
	 */
	@RequestMapping(method = RequestMethod.POST)
	@IgnoreSecurity
	@ApiOperation(value = "登录", httpMethod = "POST", response = Result.class, notes = "根据用户名和密码登录")
	@ApiImplicitParams({  
    	@ApiImplicitParam(name = "userName", value = "用户名",  
                required = true, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "password", value = "密码",  
                required = true, dataType = "String", paramType = "form")
	})
	public Result<String> login(String userName, String password, HttpServletResponse response) {
		Result<String> result = userService.login(userName, password);
		if (result.getCode() == Result.SUCCESS) {
			Map<String,Object> claims = new HashMap<String,Object>();
			claims.put("userName", userName);
			String token = tokenService.createToken(claims, ShiroUtils.encryptPassword(password, userName));
			logger.debug(userName +" 用户登录生成的Token: " + token);
			result.setMsg("登录成功");
			result.setData(token);
		}else{
			result.setMsg("登录失败," + result.getMsg());
		}
		return result;
	}

	/**
	 *  注销处理
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	@IgnoreSecurity
	@ApiOperation(value = "注销", httpMethod = "DELETE", response = Result.class, notes = "注销")
	public Result<String> logout(HttpServletRequest request) {
		Result<String> result = new Result<String>();
		String token = request.getHeader(StatelessToken.HEADER);
		//清除token
		logger.debug("清除Token: " + token);
		//tokenService.deleteToken(token);
		// 登出操作
		ShiroUtils.logout();
		logger.debug("注销成功");
		result.setCode(Result.SUCCESS);
		result.setMsg("注销成功");
		return result;
	}
}
