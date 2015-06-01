package com.tlal.vms.vms.func.user.sbook.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.admin.car.dao.CarDAO;
import com.tlal.vms.vms.func.user.finishbook.dao.FinishSBookDAO;
import com.tlal.vms.vms.func.user.finishbook.entity.FinishSBook;
import com.tlal.vms.vms.func.user.sbook.action.UserSbookSearch;
import com.tlal.vms.vms.func.user.sbook.dao.SBookDAO;
import com.tlal.vms.vms.func.user.sbook.entity.SBook;
import com.tlal.vms.vms.func.user.sbook.pojo.UserSBookPOJO;

@Service
public class UserSbookService implements UserSbookIService{
	@Resource
	private SBookDAO sBookDAO;
	@Resource
	private FinishSBookDAO finishSBookDAO;
	@Resource
	private CarDAO carDAO;

	@Override
	public Pager<UserSBookPOJO> findSBookByPage(UserSbookSearch search) {
		Pager<UserSBookPOJO> page = new Pager<UserSBookPOJO>();
		page.setList(sBookDAO.findSBookByPage(search));
		page.setCount(sBookDAO.countSBook(search));
		return page;
	}

	@Override
	public SBook findSbookById(String idsbook) {
		return sBookDAO.findSBookById(idsbook);
	}

	@Override
	public void addFinishSBook(FinishSBook finishSBook) {
		finishSBookDAO.addFinishSBook(finishSBook);
	}

	@Override
	public void deleteSbookById(String idsbook) {
		sBookDAO.deleleSBook(idsbook);
	}

	@Override
	public void setCarUnRent(String carid) {
		carDAO.setCarUnRent(carid);
	}
}
