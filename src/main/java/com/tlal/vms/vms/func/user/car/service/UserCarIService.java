package com.tlal.vms.vms.func.user.car.service;

import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.admin.car.entity.Car;
import com.tlal.vms.vms.func.user.car.action.UserCarSearch;
import com.tlal.vms.vms.func.user.sbook.entity.SBook;

public interface UserCarIService {
	public Pager<Car> findCarByPage(UserCarSearch search);
	public void rentCar(SBook sBook);
	public void setCarRentState(String carid);
}
