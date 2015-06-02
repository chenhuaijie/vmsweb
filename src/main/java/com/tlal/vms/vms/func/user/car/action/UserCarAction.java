package com.tlal.vms.vms.func.user.car.action;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.tlal.vms.base.action.BaseAction;
import com.tlal.vms.base.enums.enumc.CarOriginEnum;
import com.tlal.vms.base.enums.enumc.CarStatusEnum;
import com.tlal.vms.base.enums.enumc.ISEmpireEnum;
import com.tlal.vms.base.enums.enumc.TipTypeEnum;
import com.tlal.vms.base.utils.PageModel;
import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.admin.car.entity.Car;
import com.tlal.vms.vms.func.user.car.service.UserCarIService;
import com.tlal.vms.vms.func.user.sbook.entity.SBook;

@Namespace("/user/car")
public class UserCarAction extends BaseAction{
	private static final long serialVersionUID = 5154530675312702429L;
	private UserCarModel model = new UserCarModel();
	@Override
	public Object getModel() {
		return model;
	}
	
	@Resource
	private UserCarIService userCarService;
	/**
	 * 列表显示
	 * @return
	 */
	@Action(value="goMain",results={@Result(location="/pages/user/car/UserCarMain.jsp")})
	public String goMain(){
		model.setStatus(CarStatusEnum.values());
		model.setOrigins(CarOriginEnum.values());
		//获取页面传过来的页码和页面大小
		PageModel pageModel = model.getPageModel();
		UserCarSearch search = model.getSearch();
		int toPage = pageModel.getToPage()==null?1:pageModel.getToPage();
		search.setToPage(toPage);
		Pager<Car> page = userCarService.findCarByPage(search);
		model.setCars(page.getList());
		pageModel.setCurrent_page(toPage);
		pageModel.countPage(page.getCount());
		return SUCCESS;
	}
	
	@Action(value="doRentCar",results={@Result(location="/pages/user/car/UserCarMain.jsp")})
	public String doRentCar(){
		SBook sbook = model.getSbook();
		sbook.setIdsbook(UUID.randomUUID().toString().trim());
		sbook.setIsexpire(ISEmpireEnum.N.getEnValue());//默认未过期
		try {
			userCarService.rentCar(sbook);
			userCarService.setCarRentState(sbook.getCarid().toString().trim());//设置车辆已被租用
			addMsg("车辆租赁成功！", TipTypeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			addMsg("车辆租赁失败！", TipTypeEnum.ERROR);
		}
		return goMain();
	}
}
