package com.tlal.vms.base.sysparam;

import java.io.IOException;
import java.util.Properties;

/**
 * 系统参数
 * @author Administrator
 *
 */
public class SysParams {
	private static Integer page_size;
	private static String resetpwd;
	static{
		Properties properties=new Properties();
		try {
			properties.load(SysParams.class.getClassLoader().getResourceAsStream("sysparams.properties"));
			page_size=Integer.parseInt(properties.getProperty("page_size").toString().trim());
			resetpwd=properties.getProperty("resetpwd");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static Integer getPage_size() {
		return page_size;
	}

	public static void setPage_size(Integer page_size) {
		SysParams.page_size = page_size;
	}

	public static String getResetpwd() {
		return resetpwd;
	}

	public static void setResetpwd(String resetpwd) {
		SysParams.resetpwd = resetpwd;
	}

	public static void main(String[] args){
		System.out.println(page_size+1);
	}
}
