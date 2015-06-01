package com.tlal.vms.vms.func.admin.user.action;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.tlal.vms.base.action.BaseAction;
import com.tlal.vms.base.enums.enumc.TipTypeEnum;
import com.tlal.vms.base.enums.enumc.UserTypeEnum;
import com.tlal.vms.base.sysparam.SysParams;
import com.tlal.vms.base.utils.JsonModel;
import com.tlal.vms.base.utils.PageModel;
import com.tlal.vms.base.utils.Pager;
import com.tlal.vms.vms.func.admin.user.pojo.AdminUserPOJO;
import com.tlal.vms.vms.func.admin.user.service.AdminUserIService;
import com.tlal.vms.vms.sys.login.dao.UserDAO;
import com.tlal.vms.vms.sys.login.entity.User;
@Namespace("/admin/user")
public class AdminUserAction extends BaseAction{
	private static final long serialVersionUID = 516496322997104192L;

	private AdminUserModel model = new AdminUserModel();
	@Override
	public Object getModel() {
		return model;
	}
	
	@Resource
	private AdminUserIService adminUserService;
	@Resource
	private UserDAO userDAO;
	/**
	 * 列表显示
	 * @return
	 */
	@Action(value="goMain",results={@Result(location="/pages/admin/user/AdminUserMain.jsp")})
	public String goMain(){
		//获取页面传过来的页码和页面大小
		PageModel pageModel = model.getPageModel();
		AdminUserSearch search = model.getSearch();
		int toPage = pageModel.getToPage()==null?1:pageModel.getToPage();
		search.setToPage(toPage);
		Pager<AdminUserPOJO> page = adminUserService.findUsersByPage(search);
		model.setUsers(page.getList());
		pageModel.setCurrent_page(toPage);
		pageModel.countPage(page.getCount());
		return SUCCESS;
	}
	
	@Action(value="doDel",results={@Result(location="/pages/admin/user/AdminUserMain.jsp")})
	public String doDel(){
		String userid = model.getUserid();
		
		try {
			adminUserService.deleteUserById(userid);
			addMsg("删除成功！", TipTypeEnum.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMsg("该用户关联到台账表里的记录！", TipTypeEnum.WARN);
		}
		return goMain();
	}
	

	@Action(value="resetPwd")
	public String resetPwd(){
		JsonModel jsonModel = new JsonModel();
		String userid = model.getUserid();
		String resultString = adminUserService.resetUserPwd(userid);
		if(resultString!=null){
			jsonModel.setSuccess(true);
		}else {
			jsonModel.setSuccess(false);
		}
		super.writeJson(jsonModel);
		return NONE;
	}
	
	/**
	 * 增加
	 * @return
	 */
	@Action(value="doAdd",results={@Result(location="/pages/admin/user/AdminUserMain.jsp")})
	public String doAdd(){
		User user = model.getUser();
		user.setUserid(UUID.randomUUID().toString());
		user.setRole(UserTypeEnum.USER.getEnValue());
		user.setPassword(SysParams.getResetpwd().trim().toString());
		try {
			adminUserService.addUser(user);
			addMsg("添加成功！", TipTypeEnum.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMsg("添加失败！", TipTypeEnum.ERROR);
		}
		return goMain();
	}
	
	/**
	 * 添加用户时验证用户名是否可用
	 * @return
	 */
	@Action(value="isUserExists")
	public String isUserExists(){
		JsonModel jsonModel = new JsonModel();
		String name = model.getName();
		User user = userDAO.findByUserName(name);
		if(user!=null){
			jsonModel.setSuccess(true);
			jsonModel.setMsg("用户名已存在!");
		}else{
			jsonModel.setSuccess(false);
			jsonModel.setMsg("用户名不存在!");
		}
		super.writeJson(jsonModel);
		return NONE;
	}
}
