package com.tlal.vms.vms.func.admin.car.service;

import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.admin.car.action.AdminCarSearch;
import com.tlal.vms.vms.func.admin.car.entity.Car;

public interface AdminCarIService {
	public Pager<Car> findCarByPage(AdminCarSearch search);
	public void addCar(Car car);
	public Car findCarByPlateNum(String plate_num);
	public void deleteCarById(String carid);
	public Car findCarById(String carid);
	public void updateCar(Car car);
}
