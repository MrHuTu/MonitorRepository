package com.zhongda.monitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhongda.monitor.account.service.UserService;
import com.zhongda.monitor.business.service.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MonitorApplicationTests {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private ItemService itemService;
	
	@Test
	public void contextLoads() {
		userService.selectByUserName("admin");
		System.out.println(userService.getClass());
		System.out.println(itemService.getClass());
	}

}
