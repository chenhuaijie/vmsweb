package com.tlal.vms.base.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

public class BaseAjax {
	/**
	 * 把Object对象以json方式写回前台
	 * @param object
	 */
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
}
