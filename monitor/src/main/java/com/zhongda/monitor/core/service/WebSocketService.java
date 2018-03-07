package com.zhongda.monitor.core.service;

import com.zhongda.monitor.business.model.WiselyResponse;
/**
 * 点对点向用户推送消息接口
 * 
 * @author huchao 2018年3月7日11:25:40
 *
 */
public interface WebSocketService {
	  public void send2Users(String users, WiselyResponse msg);
}
