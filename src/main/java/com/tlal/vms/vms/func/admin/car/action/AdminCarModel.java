package com.tlal.vms.vms.func.admin.car.action;

import java.util.ArrayList;
import java.util.List;

import com.tlal.vms.base.enums.enumc.CarOriginEnum;
import com.tlal.vms.base.enums.enumc.CarStatusEnum;
import com.tlal.vms.base.utils.PageModel;
import com.tlal.vms.vms.func.admin.car.entity.Car;

public class AdminCarModel {
	private PageModel pageModel = new PageModel();
	private AdminCarSearch search = new AdminCarSearch();
	private List<Car> cars = new ArrayList<Car>();
	
	private CarStatusEnum[] status;
	private CarOriginEnum[] origins;
	private String plate_num;
	private Car car;
	private String carid;
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public AdminCarSearch getSearch() {
		return search;
	}
	public void setSearch(AdminCarSearch search) {
		this.search = search;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public CarStatusEnum[] getStatus() {
		return status;
	}
	public void setStatus(CarStatusEnum[] status) {
		this.status = status;
	}
	public CarOriginEnum[] getOrigins() {
		return origins;
	}
	public void setOrigins(CarOriginEnum[] origins) {
		this.origins = origins;
	}
	public String getPlate_num() {
		return plate_num;
	}
	public void setPlate_num(String plate_num) {
		this.plate_num = plate_num;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public String getCarid() {
		return carid;
	}
	public void setCarid(String carid) {
		this.carid = carid;
	}
}
