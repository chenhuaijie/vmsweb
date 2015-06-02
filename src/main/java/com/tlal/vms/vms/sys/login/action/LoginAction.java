package com.tlal.vms.vms.sys.login.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import com.tlal.vms.base.action.BaseAction;
import com.tlal.vms.base.utils.WebUtil;
import com.tlal.vms.vms.sys.login.entity.User;
import com.tlal.vms.vms.sys.login.service.LoginIService;

/**
 * 项目名称：VMS
 * 类描述：登陆控制的Action
 * 作者：陈怀结
 */
@Namespace("/login")
@Scope("prototype")
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 4907742518949115311L;
	private LoginModel model = new LoginModel();
	@Override
	public Object getModel() {
		return model;
	}
	@Resource
	private LoginIService loginService;
	
	@Action(value="toLogin",results={@Result(type="dispatcher",location="/pages/login/login.jsp")})
	public String toLogin(){
		return SUCCESS;
	}
	/**
	 * 登陆
	 * @return
	 */
	@Action(value="doLogin",results={
			@Result(name="super",type="dispatcher",location="/pages/super/SuperMain.jsp"),
			@Result(name="admin",type="dispatcher",location="/pages/admin/AdminMain.jsp"),
			@Result(name="user",type="dispatcher",location="/pages/user/UserMain.jsp"),	
			@Result(name="login",type="dispatcher",location="/pages/login/login.jsp")	
		}
	)
	public String doLogin(){
		User user = loginService.doLogin(model.getUser());
		HttpSession  session = WebUtil.getCurrentSession();
		HttpServletRequest request = WebUtil.getCurrentServletRequest();
		if(user==null){//用户名不存在，返回登陆页面，并提示"用户名不存在"
			request.setAttribute("msg", "用户名不存在！");
			return "login";
		}else{		//存在
				//密码错误，返回登陆页面，并提示"密码错误"
				if(!user.getPassword().trim().toString().equals(model.getUser().getPassword())){
					request.setAttribute("msg", "密码错误！");
					return "login";
				}else{
					String userType = user.getRole();
					session.setAttribute("name", user.getName());
					session.setAttribute("nickname", user.getNickname());
					session.setAttribute("userid", user.getUserid());
					session.setAttribute("password", user.getPassword());
					if(userType.toString().trim().equals("super".toString().trim())){
						return "super";
					}else if(userType.toString().trim().equals("admin".toString().trim())){
						return "admin";
					}else
						return "user";
					}
				}
				
		}

	/**
	 * 登出
	 * @return
	 */
	@Action(value="loginOut",results={@Result(type="dispatcher",location="/pages/login/login.jsp")})	
	public String loginOut(){
		//销毁sessio
		WebUtil.getCurrentSession().removeAttribute("name");
		WebUtil.getCurrentSession().removeAttribute("nickname");
		WebUtil.getCurrentSession().removeAttribute("userid");
		WebUtil.getCurrentSession().removeAttribute("password");
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@Action(value="editPwd")	
	public String editPwd(){
		return NONE;
	}
}
