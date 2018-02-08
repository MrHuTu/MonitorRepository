package com.zhongda.monitor.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.zhongda.monitor.business.mapper.ItemMapper;
import com.zhongda.monitor.business.model.Item;
import com.zhongda.monitor.business.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
@Autowired
ItemMapper itemMapper;


	@Override
	public List<Item> getAllItem() {
		
		return itemMapper.getAllItem();
	}
	
}
	
