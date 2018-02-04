package com.zhongda.monitor.core.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhongda.monitor.account.model.User;

/**
 * Title : ShiroSession监听类
 * Description : 监听shiroSession的状态进行相应的处理
 * @Author dengzm
 * @Date 2018年1月29日 下午7:39:12
 */
public class ShiroSessionListener extends SessionListenerAdapter{
	
	public static final Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);
		
	//会话过期时触发
	@Override
	public void onExpiration(Session session) {
		logger.error("会话session过期,sessionId:"+session.getId());
		User user = (User) session.getAttribute("userInfo");
		if(null != user){
			//PushMessage.userSet.remove(user.getUserName());
		}
		
	}
	
	//会话停止时触发
	@Override
	public void onStop(Session session) {
		logger.error("会话session停止,sessionId:"+session.getId());
		User user = (User) session.getAttribute("userInfo");
		if(null != user){
			//PushMessage.userSet.remove(user.getUserName());
		}
	}
}
