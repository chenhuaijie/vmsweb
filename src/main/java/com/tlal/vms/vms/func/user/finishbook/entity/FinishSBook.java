package com.tlal.vms.vms.func.user.finishbook.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tlal.vms.base.enums.enumc.ISEmpireEnum;
import com.tlal.vms.base.utils.EnumUtil;

public class FinishSBook implements Serializable{
	private static final long serialVersionUID = -1154693572521023014L;
	//流水号，主键
	private String idsbook;
	//经办人id
	private String userid;
	//汽车id
	private String carid;
	//乘租方
	private String lessee;
	//出租放方\n租出车辆的公司
	private String lease;
	//起租时间
	private Date startdate;
	//到期时间
	private Date enddate;
	private transient String startDateString;
	private transient String endDateString;
	//使用部门
	private String usedept;
	//是否过期
	private String isexpire;
	private transient String isexpireString;
	//实际归还时间
	private Date actual_date;
	private transient String actual_dateString;
	private String driver;
	private String comment;
    
	
	public String getIdsbook() {
		return idsbook;
	}
	public void setIdsbook(String idsbook) {
		this.idsbook = idsbook;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCarid() {
		return carid;
	}
	public void setCarid(String carid) {
		this.carid = carid;
	}
	public String getLessee() {
		return lessee;
	}
	public void setLessee(String lessee) {
		this.lessee = lessee;
	}
	public String getLease() {
		return lease;
	}
	public void setLease(String lease) {
		this.lease = lease;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getUsedept() {
		return usedept;
	}
	public void setUsedept(String usedept) {
		this.usedept = usedept;
	}
	public String getIsexpire() {
		return isexpire;
	}
	public void setIsexpire(String isexpire) {
		this.isexpire = isexpire;
	}
	public Date getActual_date() {
		return actual_date;
	}
	public void setActual_date(Date actual_date) {
		this.actual_date = actual_date;
	}
	public String getStartDateString() {
		SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return sdfDateFormat.format(this.getStartdate());
	}
	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}
	public String getEndDateString() {
		SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return sdfDateFormat.format(this.getEnddate());
	}
	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}
	public String getIsexpireString() {
		return  EnumUtil.getNameByValue(ISEmpireEnum.class, isexpire);
	}
	public void setIsexpireString(String isexpireString) {
		this.isexpireString = isexpireString;
	}
	public String getActual_dateString() {
		SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return sdfDateFormat.format(this.getActual_date());
	}
	public void setActual_dateString(String actual_dateString) {
		this.actual_dateString = actual_dateString;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
