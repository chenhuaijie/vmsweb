package com.tlal.vms.vms.func.user.car.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.admin.car.dao.CarDAO;
import com.tlal.vms.vms.func.admin.car.entity.Car;
import com.tlal.vms.vms.func.user.car.action.UserCarSearch;
import com.tlal.vms.vms.func.user.sbook.dao.SBookDAO;
import com.tlal.vms.vms.func.user.sbook.entity.SBook;

@Service
public class UserCarService implements UserCarIService{
	@Resource
	private CarDAO carDAO;
	@Resource
	private SBookDAO sBookDAO;

	@Override
	public Pager<Car> findCarByPage(UserCarSearch search) {
		Pager<Car> page = new Pager<Car>();
		page.setList(carDAO.findCarByPage_User(search));
		page.setCount(carDAO.countCars_User(search));
		return page;
	}

	@Override
	public void rentCar(SBook sBook) {
		sBookDAO.addSBook(sBook);
	}

	@Override
	public void setCarRentState(String carid) {
		carDAO.setCarRent(carid);
	}
}
