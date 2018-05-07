package com.zhongda.monitor.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.UserProjectMapper;
import com.zhongda.monitor.business.model.UserProject;
import com.zhongda.monitor.business.service.UserProjectService;

@Service
public class UserProjectServiceImpl implements UserProjectService {

	@Resource
	private UserProjectMapper userProjectMapper;

	@Override
	public Map<Integer, List<String>> selectAllPuser() {
		List<UserProject> userProjects = userProjectMapper.selectAllPuser();
		Map<Integer, List<String>> hashMap = new HashMap<Integer, List<String>>();
		for (UserProject userProject : userProjects) {
			Integer key = userProject.getProjectId();
			List<String> list = hashMap.get(key);
			if (null == list) {
				list = new ArrayList<String>();
			}
			list.add(userProject.getUserName());
			hashMap.put(key, list);
		}
		return hashMap;
	}

	@Override
	public Map<Integer, List<String>> selectAllUpro() {
		List<UserProject> selectAllUpro = userProjectMapper.selectAllUpro();
		Map<Integer, List<String>> hashMap = new HashMap<Integer, List<String>>();
		for (UserProject userProject : selectAllUpro) {
			Integer key = userProject.getUserId();
			List<String> list = hashMap.get(key);
			if (null == list) {
				list = new ArrayList<String>();
			}
			list.add(userProject.getProjectName());
			hashMap.put(key, list);
		}
		return hashMap;
	}

}
