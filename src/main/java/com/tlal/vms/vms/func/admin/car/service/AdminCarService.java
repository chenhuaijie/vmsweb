package com.tlal.vms.vms.func.admin.car.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.admin.car.action.AdminCarSearch;
import com.tlal.vms.vms.func.admin.car.dao.CarDAO;
import com.tlal.vms.vms.func.admin.car.entity.Car;

@Service
public class AdminCarService implements AdminCarIService{
	@Resource
	private CarDAO carDAO;

	@Override
	public Pager<Car> findCarByPage(AdminCarSearch search) {
		Pager<Car> page = new Pager<Car>();
		page.setList(carDAO.findCarByPage(search));
		page.setCount(carDAO.countCars(search));
		return page;
	}

	@Override
	public void addCar(Car car) {
		carDAO.addCar(car);
	}

	@Override
	public Car findCarByPlateNum(String plate_num) {
		return carDAO.findCarByPlateNum(plate_num);
	}

	@Override
	public void deleteCarById(String carid) {
		carDAO.deleteCarById(carid);
	}

	@Override
	public Car findCarById(String carid) {
		return carDAO.findCarById(carid);
	}

	@Override
	public void updateCar(Car car) {
		// TODO Auto-generated method stub
		carDAO.updateCar(car);
	}

	
}
