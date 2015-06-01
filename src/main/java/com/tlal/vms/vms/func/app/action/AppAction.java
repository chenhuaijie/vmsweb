package com.tlal.vms.vms.func.app.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;

import com.tlal.vms.base.action.BaseAction;
import com.tlal.vms.vms.func.app.pojo.AppEJB;
import com.tlal.vms.vms.func.app.service.AppIService;

@Namespace("/app")
@Scope("prototype")
public class AppAction extends BaseAction{
	private static final long serialVersionUID = 3071552226246575566L;
	private AppModel model = new AppModel();
	@Override
	public Object getModel() {
		return model;
	}
	@Resource
	private AppIService appService;
	
	@Action(value="findCarByPlateNum")
	public String findCarByPlateNum(){
		String plate_num = model.getPlate_num();
		AppEJB ejb = appService.findCarByPlateNum(plate_num);
		super.writeJson(ejb);
		return SUCCESS;
	}
}
