package com.tlal.vms.vms.func.user.sbook.service;

import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.user.finishbook.entity.FinishSBook;
import com.tlal.vms.vms.func.user.sbook.action.UserSbookSearch;
import com.tlal.vms.vms.func.user.sbook.entity.SBook;
import com.tlal.vms.vms.func.user.sbook.pojo.UserSBookPOJO;

public interface UserSbookIService {
	/**
	 * 分页查找用户信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<UserSBookPOJO> findSBookByPage(UserSbookSearch search);
	
	public SBook findSbookById(String idsbook);
	
	public void addFinishSBook(FinishSBook finishSBook);
	
	public void deleteSbookById(String idsbook);
	
	public void setCarUnRent(String carid);
}
