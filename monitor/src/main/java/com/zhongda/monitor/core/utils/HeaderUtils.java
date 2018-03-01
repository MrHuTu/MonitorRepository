package com.zhongda.monitor.core.utils;

import javax.servlet.http.HttpServletRequest;

import com.zhongda.monitor.account.security.StatelessToken;

/**
 * 
 * Title : HeaderUtils管理
 * Description : HeaderUtils工具类
 * @Author dengzm
 * @Date 2018年3月1日 下午5:47:58
 */
public class HeaderUtils {
	
	/**
	 * 获取请求头中的token字符串
	 * @param request 请求头
	 * @return 返回token字符串
	 */
	public static String getTokenFromRequest(HttpServletRequest request){
		String authorization = request.getHeader(StatelessToken.DEFAULT_TOKEN_NAME);
		if (null != authorization && authorization.startsWith(StatelessToken.TOKEN_HEADER_PREFIX)){
			//截取token得到jwt格式的token信息
			return authorization.substring(StatelessToken.TOKEN_HEADER_PREFIX.length() + 1);
		}
		return null;
	}
}