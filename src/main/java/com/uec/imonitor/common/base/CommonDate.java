package com.uec.imonitor.common.base;

import java.util.Date;

import com.uec.imonitor.common.util.CommonUtil;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 时间类型 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class CommonDate {

	private Date date;//时间类型
	private String year;//年
	private String month;//月
	private String day;//天
	public CommonDate(){
		
	}
	public CommonDate(Date date){
		this.date = date;
		this.year = CommonUtil.getYear(date);
		this.month = CommonUtil.getMonth(date);
		this.day = CommonUtil.getDay(date);
				
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
}
