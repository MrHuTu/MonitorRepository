package com.zhongda.monitor.core.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.model.WiselyResponse;
import com.zhongda.monitor.core.service.WebSocketService;


@Service
public class WebSocketServiceImpl  implements WebSocketService{
	 @Autowired
	    private SimpMessagingTemplate template;

	    

	    /**
	     * 发送给指定用户
	     * @param users
	     * @param msg
	     */
	   
	    public void send2Users(String users, WiselyResponse msg) {	    		        
	           template.convertAndSendToUser(users, "/msg", msg);	       
	    }
}
