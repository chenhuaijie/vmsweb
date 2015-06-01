package com.tlal.vms.vms.sys.login.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tlal.vms.vms.sys.login.dao.UserDAO;
import com.tlal.vms.vms.sys.login.entity.User;

@Service
public class LoginService implements LoginIService {

	@Resource
	private UserDAO userDAO;
	
	/**
	 * 登陆方法的实现
	 */
	@Override
	public User doLogin(User user) {
		User u = userDAO.findByUserName(user.getName());
		if(u==null){
			return null;
		}else{
			return u;
		}
	}
}
