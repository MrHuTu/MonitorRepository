package com.zhongda.monitor.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	ValidateCodeService validateCodeService;

	@GetMapping("/getCode")

	@ApiOperation(value = "获取图片验证码", httpMethod = "GET", notes = "获取图片验证码")
	public void getValidateCode(HttpServletRequest request,
			HttpServletResponse response) {

		validateCodeService.getValiCode(request, response);
		
	}
	/**
	 * 验证验证码是否正确
	 * @param code
	 * @return
	 */
	@PostMapping("/revisionCode")
	@ResponseBody
	@ApiOperation(value = "验证码比对", httpMethod = "POST", response = Result.class, notes = "验证码比对")
	public Result<String>  revisionValiCode ( String code) {
		
		return validateCodeService.revisionValiCode(code);
	}
	
	/**
	 * 发送邮箱或短信修改密码
	 */
	@PostMapping("/sendValidateCode")
	@ResponseBody
	@ApiOperation(value = "发送忘记密码验证码", httpMethod = "POST", response = Result.class, notes = "发送忘记密码验证码")
	public Result<String> sendValidateCode(){
		return validateCodeService.sendValidateCode();
	} 
	
}
