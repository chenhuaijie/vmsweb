package com.tlal.vms.vms.sys.supers.mine.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tlal.vms.vms.sys.login.dao.UserDAO;
import com.tlal.vms.vms.sys.login.entity.User;

@Service
public class SuperMineService implements SuperMineIService{
	@Resource
	private UserDAO userDAO;
	
	@Override
	public User findSuperMineInfo(String userid) {
		return userDAO.findById(userid);
	}
}
