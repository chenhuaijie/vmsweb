package com.tlal.vms.vms.func.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tlal.vms.base.enums.enumc.CarStatusEnum;
import com.tlal.vms.vms.func.admin.car.dao.CarDAO;
import com.tlal.vms.vms.func.admin.car.entity.Car;
import com.tlal.vms.vms.func.app.pojo.AppEJB;
import com.tlal.vms.vms.func.app.pojo.AppPOJO;
import com.tlal.vms.vms.func.user.sbook.dao.SBookDAO;
import com.tlal.vms.vms.func.user.sbook.entity.SBook;
import com.tlal.vms.vms.sys.login.dao.UserDAO;
import com.tlal.vms.vms.sys.login.entity.User;

@Service
public class APPService implements AppIService{

	@Resource
	private CarDAO carDAO;
	@Resource
	private SBookDAO sBookDAO;
	@Resource
	private UserDAO userDAO;
	@Override
	public AppEJB findCarByPlateNum(String plate_num) {
		AppEJB appEJB = new AppEJB();
		List<Car> cars = carDAO.findCarLikePlateNum(plate_num);
		if(cars!=null&&cars.size()>0){
			appEJB.setExists(true);//表示找到了匹配的车
			
			List<AppPOJO> appPOJOs = new ArrayList<AppPOJO>();
			
			for(Car car:cars){
				AppPOJO pojo = new AppPOJO();
				pojo.setCar(car);
				if(car.getStatus().equals(CarStatusEnum.Y.getEnValue())){//已租用的情况下还需要附加上租赁情况
					SBook book = sBookDAO.findSBookByCarid(car.getCarid().toString().trim());
					pojo.setBook(book);
					User user = userDAO.findById(book.getUserid());
					User user2 = new User();
					user2.setName(user.getName());
					user2.setDept(user.getDept());
					user2.setCompany(user.getCompany());
					pojo.setUser(user2);
				}
				appPOJOs.add(pojo);
			}
			appEJB.setCars(appPOJOs);
		}else{
			appEJB.setExists(false);//表示没有找到匹配的车
			appEJB.setCars(null);
		}
		return appEJB;
	}
}
