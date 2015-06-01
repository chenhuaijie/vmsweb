package com.tlal.vms.vms.func.user.sbook.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.transaction.Transaction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;

import com.tlal.vms.base.action.BaseAction;
import com.tlal.vms.base.enums.enumc.CarOriginEnum;
import com.tlal.vms.base.enums.enumc.ISEmpireEnum;
import com.tlal.vms.base.enums.enumc.TipTypeEnum;
import com.tlal.vms.base.utils.PageModel;
import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.user.finishbook.entity.FinishSBook;
import com.tlal.vms.vms.func.user.sbook.entity.SBook;
import com.tlal.vms.vms.func.user.sbook.pojo.UserSBookPOJO;
import com.tlal.vms.vms.func.user.sbook.service.UserSbookIService;
import com.tlal.vms.vms.sys.login.dao.UserDAO;
import com.tlal.vms.vms.sys.login.entity.User;

@Namespace("/user/sbook")
public class UserSbookAction extends BaseAction{
	private static final long serialVersionUID = -1647010349419732558L;
	private UserSbookModel model = new UserSbookModel();
	@Override
	public Object getModel() {
		return model;
	}
	
	@Resource
	private UserSbookIService userSbookService;
	@Resource
	private UserDAO userDAO;
	
	@Action(value="goMain",results={@Result(location="/pages/user/sbook/UserSBookMain.jsp")})
	public String goMain(){
		List<User> users = userDAO.findAllUsers();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String, String> map = null;
		for(User u:users){
			map = new HashMap<String, String>();
			map.put("id", u.getUserid());
			map.put("name", u.getName());
			list.add(map);
		}
		model.setUsers(list);
		model.setOrgins(CarOriginEnum.values());
		model.setStatus(ISEmpireEnum.values());
		//获取页面传过来的页码和页面大小
		PageModel pageModel = model.getPageModel();
		UserSbookSearch search = model.getSearch();
		int toPage = pageModel.getToPage()==null?1:pageModel.getToPage();
		search.setToPage(toPage);
		Pager<UserSBookPOJO> page = userSbookService.findSBookByPage(search);
		model.setSbooks(page.getList());
		pageModel.setCurrent_page(toPage);
		pageModel.countPage(page.getCount());
		return SUCCESS;
	}
	
	@Action(value="doReturnCar",results={@Result(location="/pages/user/sbook/UserSBookMain.jsp")})
	public String doReturnCar(){
		String idsbook =  model.getIdsbook().toString().trim();
		SBook sbook = userSbookService.findSbookById(idsbook);
		FinishSBook finishSBook = new FinishSBook();
		BeanUtils.copyProperties(sbook, finishSBook);//这几个值是空的，需要去掉
		Date nowDate = new Date();
		if(sbook.getEnddate().before(nowDate)){//已过期还车
			finishSBook.setIsexpire(ISEmpireEnum.Y.getEnValue());
		}else{
			finishSBook.setIsexpire(ISEmpireEnum.N.getEnValue());
		}
		finishSBook.setActual_date(nowDate);//实际归还时间
		try {
			userSbookService.addFinishSBook(finishSBook);//添加记录到已完成表
			userSbookService.deleteSbookById(idsbook);//删除未完成记录
			userSbookService.setCarUnRent(sbook.getCarid().toString().trim());//设置车辆未被租用
			addMsg("车辆退租成功！", TipTypeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			addMsg("车辆退租失败！", TipTypeEnum.ERROR);
		}
		return goMain();
	}
}
