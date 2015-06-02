package com.tlal.vms.vms.func.admin.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tlal.vms.base.sysparam.SysParams;
import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.admin.user.action.AdminUserSearch;
import com.tlal.vms.vms.func.admin.user.pojo.AdminUserPOJO;
import com.tlal.vms.vms.sys.login.dao.UserDAO;
import com.tlal.vms.vms.sys.login.entity.User;

@Service
public class AdminUserService implements AdminUserIService{
	@Resource
	private UserDAO userDAO;
	
	@Override
	public Pager<AdminUserPOJO> findUsersByPage(AdminUserSearch search) {
		Pager<AdminUserPOJO> page = new Pager<AdminUserPOJO>();
		page.setList(userDAO.findUsersByPage(search));
		page.setCount(userDAO.countUsers(search));
		return page;
	}

	@Override
	public String deleteUserById(String userid) {
		userDAO.deleteUser(userid);
		return userid;
	}

	@Override
	public String resetUserPwd(String userid) {
		User user = userDAO.findById(userid);
		user.setPassword(SysParams.getResetpwd());
		userDAO.updateUser(user);
		return userid;
	}

	@Override
	public User addUser(User user) {
		userDAO.addUser(user);
		return user;
	}

}
