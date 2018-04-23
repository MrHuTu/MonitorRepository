package com.zhongda.monitor.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.core.constant.CommonConstant;
import com.zhongda.monitor.core.exception.VaildCodeExpireException;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.core.service.MailService;
import com.zhongda.monitor.core.service.ValidateCodeService;
import com.zhongda.monitor.core.utils.CacheUtils;
import com.zhongda.monitor.core.utils.MailUtils;
import com.zhongda.monitor.core.utils.ValidateCodeUtils;
import com.zhongda.monitor.core.utils.sms.SmsContentTemplate;
import com.zhongda.monitor.core.utils.sms.SmsSender;

/**
 * 
 * @author KCheng
 *
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

	@Resource
	private MailService mailService;
	
	private ValidateCodeUtils validateCodeUtils = new ValidateCodeUtils();
	
	/**
	 * @param  code 验证码 
	 * @param info 验证码缓存key值
	 */
	public Result<String> revisionValiCode(String code ,String info) {
		Result<String> result = new Result<String>();
		String realCode = (String) CacheUtils.get(CacheUtils.CACHE_VALICODE,
				info+"code");// 从缓存获取code
		if(null == realCode){
			throw new VaildCodeExpireException("验证码有效期已过，请重新输入");
		}
		// 进行对比
		if (realCode.equalsIgnoreCase(code)) {
			result.success("验证码正确");
		} else {
			result.failure("验证码输入错误！");
		}
		return result;
	}
	
	/**
	 *@param  millis 毫秒数
	 */
	public void getValiCode(HttpServletRequest request,
			HttpServletResponse response  ,String millis) {
		response.setContentType("image/jpeg");// 设置响应类型，告知浏览器输出的是图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Set-Cookie", "name=value; HttpOnly");// 设置HttpOnly属性,防止Xss攻击
		response.setDateHeader("Expire", 0);
		try {
			validateCodeUtils.getRandomCode(request, response,millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param info 电话号码或者手机号
	 * @return 发送短信结果
	 */
	public Result<String> sendValidateCode(String userId) {
		Result< String > result  = new Result<String>();
		//生成验证码
		String code = (int) (Math.random() * 9000 + 1000) +"";
		User user =  (User) CacheUtils.get(CacheUtils.CACHE_USER, userId+"user");
		if(null == user){
			throw new VaildCodeExpireException("页面有效期为30分钟，您已超过有效期，请刷新重试！");
		}
		//将验证码存入缓存
		CacheUtils.putAndSetTimeToIdle(CacheUtils.CACHE_VALICODE, user.getUserId()+"code", code, CommonConstant.VALID_CODE_EXPIRE_TIME);
		if(user.getPhone()!=null){
			//发送短信验证码 
			List<String> params = new ArrayList<String>();
			params.add(code);
			params.add((CommonConstant.VALID_CODE_EXPIRE_TIME/60)+"");
			SmsSender sms = new SmsSender();
			sms.send(SmsContentTemplate.ResetVerifyCode,/*user.getPhone()*/"13348658366" ,params );
		}
		else{
			//发邮件验证码
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("userName", user.getUserName());
			paramsMap.put("realName",user.getRealName());
			paramsMap.put("time", new SimpleDateFormat("yyyy年MM月dd日  HH小时mm分").format(new Date()) );
			paramsMap.put("code", code);
			paramsMap.put("expireTime", CommonConstant.VALID_CODE_EXPIRE_TIME/60+"");
			mailService.sendHtmlTemplateMail(/*user.getEmail()*/"771588005@qq.com", MailUtils.CODE_SUBJECT, MailUtils.VALIDATE_CODE_TEMPLATE, paramsMap);
		}
		return result.success("发送成功");
	}
}
