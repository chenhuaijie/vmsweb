package com.tlal.vms.vms.func.user.mine.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.tlal.vms.base.action.BaseAction;
import com.tlal.vms.base.enums.enumc.TipTypeEnum;
import com.tlal.vms.base.utils.JsonModel;
import com.tlal.vms.base.utils.WebUtil;
import com.tlal.vms.vms.func.admin.mine.service.AdminMineIService;
import com.tlal.vms.vms.sys.login.dao.UserDAO;
import com.tlal.vms.vms.sys.login.entity.User;

@Namespace("/user/mine")
public class UserMineAction extends BaseAction{
	private static final long serialVersionUID = 8116858471046490745L;
	private UserMineModel model = new UserMineModel();
	@Override
	public Object getModel() {
		return model;
	}

	@Resource
	private UserDAO userDAO;
	
	/**
	 * 我的信息
	 * @return
	 */
	@Action(value="goMain",results={@Result(location="/pages/user/UserInfo.jsp")})
	public String goMain(){
		String userid = (String) WebUtil.getCurrentSession().getAttribute("userid");
		List<User> list = new ArrayList<User>();
		list.add(userDAO.findById(userid));
		model.setList(list);
		return SUCCESS;
	}
	/**
	 * 编辑我的信息
	 * @return
	 */
	@Action(value="myInfoEdit",results={@Result(location="/pages/user/UserInfo.jsp")})
	public String myInfoEdit(){
		User dto = model.getUser();
		User user = userDAO.findById(dto.getUserid());
		user.setName(dto.getName());
		user.setNickname(dto.getNickname());
		user.setEmail(dto.getEmail());
		user.setPhonenumber(dto.getPhonenumber());
		user.setDept(dto.getDept());
		user.setCompany(dto.getCompany());
		userDAO.updateUser(user);
		addMsg("信息修改成功！", TipTypeEnum.SUCCESS);
		return goMain();
	}
	
	@Action(value="editPwd")
	public String editPwd(){
		JsonModel jsonModel = new JsonModel();
		User admin = model.getUser();
		User user = userDAO.findById(admin.getUserid());
		user.setPassword(admin.getPassword());
		userDAO.updateUser(user);
		jsonModel.setSuccess(true);
		super.writeJson(jsonModel);
		return NONE;
	}
}
