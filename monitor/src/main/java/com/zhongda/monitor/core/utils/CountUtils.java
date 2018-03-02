package com.zhongda.monitor.core.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * 
 * Title : CountUtils管理
 * Description : CountUtils工具类
 * @Author dengzm
 * @Date 2018年3月1日 下午5:47:58
 */
public class CountUtils {
	
	/**
	 * 创建一个Multiset集合
	 */
	public static Multiset<String> createMultiset(){
		return HashMultiset.create();
	}
	
	/**
	 * 将multiset转换成map集合
	 */
	public static Map<String, Integer> paserToMap(Multiset<String> multiset){
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		for (Multiset.Entry<String> entry : multiset.entrySet()){
			countMap.put(entry.getElement(), entry.getCount());
	    }
		return countMap;
	}
}