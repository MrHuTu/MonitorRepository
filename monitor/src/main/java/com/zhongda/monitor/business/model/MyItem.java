package com.zhongda.monitor.business.model;

import java.util.List;

public class MyItem {
	private ItemLeft itemLeft;
	private List<ItemAvgData> itemAvgList;
	public ItemLeft getItemLeft() {
		return itemLeft;
	}
	public void setItemLeft(ItemLeft itemLeft) {
		this.itemLeft = itemLeft;
	}
	public List<ItemAvgData> getItemAvgList() {
		return itemAvgList;
	}
	public void setItemAvgList(List<ItemAvgData> itemAvgList) {
		this.itemAvgList = itemAvgList;
	}
	
}
