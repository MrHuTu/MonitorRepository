package com.zhongda.monitor.core.service;

import com.zhongda.monitor.business.model.WiselyResponse;

public interface WebSocketService {
	  public void send2Users(String users, WiselyResponse msg);
}
