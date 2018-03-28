package com.zhongda.monitor.core.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhongda.monitor.core.model.Result;

/**
 * 验证码业务接口
 * 
 * @author KCheng
 *
 */

public interface ValidateCodeService {
	// 获取验证码
	public void getValiCode(HttpServletRequest request,
			HttpServletResponse response ,String millis);

	// 验证验证码
	public Result<String> revisionValiCode(String code,String userId);
	
	public Result<String>sendValidateCode(String userId);
}
