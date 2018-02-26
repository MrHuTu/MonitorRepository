package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.business.model.Item;
import com.zhongda.monitor.business.service.impl.ItemServiceImpl;
/**
 * 
 * @author Chao.hu 2018年2月7日15:42:47
 *
 */
@RestController
@RequestMapping("/Item")
@Api(value = "项目模块", tags = { "项目操作接口" })
public class ItemController {
	@Autowired
	ItemServiceImpl ItemServiceImpl;
	@RequestMapping(value="/getAllItem",method = RequestMethod.GET)
	@ApiOperation(value = "项目信息", notes = "项目信息", code = 200, produces = "application/json" ,httpMethod = "GET")
	private List<Item> getAllItem(){
		return ItemServiceImpl.getAllItem();
		
	}
}
