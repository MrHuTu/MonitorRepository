package com.zhongda.monitor.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.core.service.ValidateCodeService;

/**
 * 验证码操作
 * 
 * @author KCheng
 *
 */
@Controller
@RequestMapping("/valiCode")
@Api(tags = { "验证码操作接口" })
public class ValidateCodeController {
	
	@Resource
	private ValidateCodeService validateCodeService;
	
	@GetMapping("/{millis}")
	@ApiOperation(value = "获取图片验证码--cheng.kong", httpMethod = "GET", notes = "获取图片验证码")
	@ApiImplicitParams({
		@ApiImplicitParam( dataType = "String", name = "millis", value = "获取图片的时间", required = true, paramType="path")
	})
	public void getValidateCode(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String millis) {
		validateCodeService.getValiCode(request, response, millis);
		
	}
	/**
	 * 验证验证码是否正确
	 * @param code
	 * @return
	 */
	@PostMapping("/revisionCode")
	@ResponseBody
	@ApiOperation(value = "验证码比对--cheng.kong", httpMethod = "POST", response = Result.class, notes = "验证码比对")
	@ApiImplicitParams({
		@ApiImplicitParam( dataType = "String", name = "code", value = "验证码", required = true, paramType="form"),
		@ApiImplicitParam( dataType = "String", name = "info", value = "验证码信息", required = true,  paramType="form")
	})
	public Result<String>  revisionValiCode (String code, String info) {
		return validateCodeService.revisionValiCode(code,info);
	}
	
	/**
	 * 发送邮箱或短信修改密码
	 */
	@PostMapping("/sendValidateCode")
	@ResponseBody
	@ApiOperation(value = "发送忘记密码验证码--cheng.kong", httpMethod = "POST", response = Result.class, notes = "发送忘记密码验证码" )
	@ApiImplicitParams({
		@ApiImplicitParam( dataType = "String", name = "userId", value = "用户ID", required = true, paramType="form"),
	})
	public Result<String> sendValidateCode(String userId){
		return validateCodeService.sendValidateCode(userId);
	} 
	
}
