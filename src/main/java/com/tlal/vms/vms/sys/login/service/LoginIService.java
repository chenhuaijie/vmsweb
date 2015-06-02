package com.tlal.vms.vms.sys.login.service;

import com.tlal.vms.vms.sys.login.entity.User;

public interface LoginIService {
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	public User doLogin(User user);
}
