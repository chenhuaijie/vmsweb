package com.tlal.vms.base.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tlal.vms.base.enums.enumc.TipTypeEnum;
import com.tlal.vms.base.utils.WebUtil;

/**
 * Action类的基类
 * @author Administrator
 *
 */
@ParentPackage("basePackage")
@Namespace("/")
public abstract class BaseAction extends ActionSupport implements ModelDriven<Object>{
	private static final long serialVersionUID = 5958973675939419750L;
	public void writeJson(Object object) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * 向页面提示信息
	 * @param msg
	 * @param tipType
	 */
	public void addMsg(String msg,TipTypeEnum tipType){
		WebUtil.getCurrentServletRequest().setAttribute("msg", msg);//提示的信息
		WebUtil.getCurrentServletRequest().setAttribute("tipType", tipType.getEnValue());//页面弹出相关的对话框
	}
}
