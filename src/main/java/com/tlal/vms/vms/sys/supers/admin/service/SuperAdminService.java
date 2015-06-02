package com.tlal.vms.vms.sys.supers.admin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tlal.vms.base.sysparam.SysParams;
import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.sys.login.dao.UserDAO;
import com.tlal.vms.vms.sys.login.entity.User;
import com.tlal.vms.vms.sys.supers.admin.action.SuperAdminSearch;
import com.tlal.vms.vms.sys.supers.admin.pojo.SuperAdminPOJO;

@Service
public class SuperAdminService implements SuperAdminIService{
	@Resource
	private UserDAO userDAO;
	
	@Override
	public Pager<SuperAdminPOJO> findAdminsByPage(SuperAdminSearch search) {
		Pager<SuperAdminPOJO> page = new Pager<SuperAdminPOJO>();
		page.setList(userDAO.findAdminsByPage(search));
		page.setCount(userDAO.countAdmins(search));
		return page;
	}

	@Override
	public String deleteAdminById(String userid) {
		userDAO.deleteUser(userid);
		return userid;
	}

	@Override
	public String resetAdminPwd(String userid) {
		User user = userDAO.findById(userid);
		user.setPassword(SysParams.getResetpwd());
		userDAO.updateUser(user);
		return userid;
	}

	@Override
	public User addAdmin(User user) {
		userDAO.addUser(user);
		return user;
	}
}
