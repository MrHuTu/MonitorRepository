package com.zhongda.monitor.business.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.core.model.Result;

/**
 * Title : 公共视图控制器
 * Description : 处理公共路径映射
 * @Author dengzm
 * @Date 2018年1月29日 下午8:33:39
 */
@RestController
@RequestMapping("/common")
public class CommonController {

	@RequestMapping("/index")
	public Result<String> index() {
		return new Result<String>().success("进入首页");
	}
	
	@RequestMapping("/login")
	public Result<String> login() {
		return new Result<String>().success("进入登录页");
	}
	
	@RequestMapping("/error")
	public Result<String> error() {
		return new Result<String>().success("进入错误页");
	}

}