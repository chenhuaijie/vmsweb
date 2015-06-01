package com.tlal.vms.vms.func.admin.mine.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tlal.vms.vms.sys.login.dao.UserDAO;
import com.tlal.vms.vms.sys.login.entity.User;

@Service
public class AdminMineService implements AdminMineIService{
	@Resource
	private UserDAO userDAO;
	
	@Override
	public User findAdminMineInfo(String userid) {
		return userDAO.findById(userid);
	}
}
