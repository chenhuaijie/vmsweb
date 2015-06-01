package com.tlal.vms.vms.sys.supers.admin.service;

import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.sys.login.entity.User;
import com.tlal.vms.vms.sys.supers.admin.action.SuperAdminSearch;
import com.tlal.vms.vms.sys.supers.admin.pojo.SuperAdminPOJO;

public interface SuperAdminIService {
	/**
	 * 分页查找管理员信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<SuperAdminPOJO> findAdminsByPage(SuperAdminSearch search);
	
	/**
	 * 根据id删除管理员
	 * @param userid
	 * @return
	 */
	public String deleteAdminById(String userid);
	
	/**
	 * 重置管理员的密码
	 * @param userid
	 */
	public String resetAdminPwd(String userid);
	
	/**
	 * 增加管理员
	 * @param user
	 * @return
	 */
	public User addAdmin(User user);
}
