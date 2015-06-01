package com.tlal.vms.vms.func.app.service;

import com.tlal.vms.vms.func.app.pojo.AppEJB;


public interface AppIService {
	public AppEJB findCarByPlateNum(String plate_num);
}
