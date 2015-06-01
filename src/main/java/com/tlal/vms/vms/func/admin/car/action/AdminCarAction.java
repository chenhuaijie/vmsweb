package com.tlal.vms.vms.func.admin.car.action;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.tlal.vms.base.action.BaseAction;
import com.tlal.vms.base.enums.enumc.CarOriginEnum;
import com.tlal.vms.base.enums.enumc.CarStatusEnum;
import com.tlal.vms.base.enums.enumc.TipTypeEnum;
import com.tlal.vms.base.utils.JsonModel;
import com.tlal.vms.base.utils.PageModel;
import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.admin.car.entity.Car;
import com.tlal.vms.vms.func.admin.car.service.AdminCarIService;

@Namespace("/admin/car")
public class AdminCarAction extends BaseAction{
	private static final long serialVersionUID = 1877523299211116686L;
	
	private AdminCarModel model = new AdminCarModel();
	@Override
	public Object getModel() {
		return model;
	}
	
	@Resource
	private AdminCarIService adminCarService;
	/**
	 * 列表显示
	 * @return
	 */
	@Action(value="goMain",results={@Result(location="/pages/admin/car/AdminCarMain.jsp")})
	public String goMain(){
		model.setStatus(CarStatusEnum.values());
		model.setOrigins(CarOriginEnum.values());
		//获取页面传过来的页码和页面大小
		PageModel pageModel = model.getPageModel();
		AdminCarSearch search = model.getSearch();
		int toPage = pageModel.getToPage()==null?1:pageModel.getToPage();
		search.setToPage(toPage);
		Pager<Car> page = adminCarService.findCarByPage(search);
		model.setCars(page.getList());
		pageModel.setCurrent_page(toPage);
		pageModel.countPage(page.getCount());
		return SUCCESS;
	}
	
	@Action(value="doAdd",results={@Result(location="/pages/admin/car/AdminCarMain.jsp")})
	public String doAdd(){
		Car car = model.getCar();
		car.setCarid(UUID.randomUUID().toString().trim());
		car.setStatus(CarStatusEnum.N.getEnValue());//默认未被租用
		try {
			adminCarService.addCar(car);
			addMsg("添加成功！", TipTypeEnum.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMsg("添加失败！", TipTypeEnum.ERROR);
		}
		return goMain();
	}
	
	/**
	 * 验证车牌号是否可用
	 * @return
	 */
	@Action(value="isPalteNumExists")
	public String isPalteNumExists(){
		String plate_num = model.getPlate_num();
		Car car = adminCarService.findCarByPlateNum(plate_num);
		JsonModel jsonModel = new JsonModel();
		if(car!=null){
			jsonModel.setSuccess(true);
			jsonModel.setMsg("车牌号已存在!");
		}else{
			jsonModel.setSuccess(false);
			jsonModel.setMsg("车牌号不存在!");
		}
		super.writeJson(jsonModel);
		return NONE;
	}
	
	@Action(value="doDel",results={@Result(location="/pages/admin/car/AdminCarMain.jsp")})
	public String doDel(){
		String carid = model.getCarid();
		try {
			adminCarService.deleteCarById(carid);
			addMsg("删除成功！", TipTypeEnum.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMsg("存在与该车辆关联的租赁记录或台账记录！", TipTypeEnum.WARN);
		}
		return goMain();
	}
	
	@Action(value="doEdit",results={@Result(location="/pages/admin/car/AdminCarMain.jsp")})
	public String doEdit(){
		Car car = model.getCar();
		Car dto = adminCarService.findCarById(car.getCarid());
		dto.setName(car.getName());
		dto.setPlate_num(car.getPlate_num());
		dto.setOrigin(car.getOrigin());
		dto.setType(car.getType());
		try {
			adminCarService.updateCar(dto);
			addMsg("修改成功！", TipTypeEnum.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMsg("修改失败！", TipTypeEnum.ERROR);
		}
		return goMain();
	}
}
