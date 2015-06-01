package com.tlal.vms.vms.func.user.finishbook.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.tlal.vms.base.action.BaseAction;
import com.tlal.vms.base.enums.enumc.CarOriginEnum;
import com.tlal.vms.base.enums.enumc.ISEmpireEnum;
import com.tlal.vms.base.utils.PageModel;
import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.user.finishbook.pojo.UserFinishSBookPOJO;
import com.tlal.vms.vms.func.user.finishbook.service.UserFinishSBookIService;
import com.tlal.vms.vms.sys.login.dao.UserDAO;
import com.tlal.vms.vms.sys.login.entity.User;

@Namespace("/user/finishSBook")
public class UserFinishSBookAction extends BaseAction{
	private static final long serialVersionUID = 300263961066925367L;
	private UserFinishSBookModel model = new UserFinishSBookModel();
	@Override
	public Object getModel() {
		return model;
	}
	
	@Resource
	private UserFinishSBookIService userFinishSBookService;
	@Resource
	private UserDAO userDAO;

	@Action(value="goMain",results={@Result(location="/pages/user/finishsbook/UserFinishSBookMain.jsp")})
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
		UserFinishSBookSearch search = model.getSearch();
		int toPage = pageModel.getToPage()==null?1:pageModel.getToPage();
		search.setToPage(toPage);
		Pager<UserFinishSBookPOJO> page = userFinishSBookService.findFinishSBookByPage(search);
		model.setFinishSBooks(page.getList());
		pageModel.setCurrent_page(toPage);
		pageModel.countPage(page.getCount());
		return SUCCESS;
	}
}
