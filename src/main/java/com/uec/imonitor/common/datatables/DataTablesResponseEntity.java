package com.uec.imonitor.common.datatables;


import java.util.List;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 封装Datatables插件的响应实体,其中【sEcho，iTotalRecords，iTotalDisplayRecords，aaData】4个参数是必须有的，不可缺少 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class DataTablesResponseEntity<T>{
	/**
	 * Script must return a JSON object with 4 keys:
	 * sEcho: just a number sent to the script and which must be returned untouched
	 * iTotalRecords: total number of elements in table
	 * iTotalDisplayRecords: total number of elements, after filtering (if not filtered, must be equal to iTotalRecords)
	 * aaData: the list of rows to display
	 * We can write this JSON object in a template file:
	 */
	
	/**
	 * <p>Description: 次数，页面发来的参数，原样返回</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields sEcho 
	 */
	private int sEcho;
	
	/**
	 * <p>Description:过滤前总记录数 </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields iTotalRecords 
	 */
	private long iTotalRecords;
	
	/**
	 * <p>Description: 过滤后总记录数，我没有使用过滤，不太清楚和iTotalRecords的区别, </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields iTotalDisplayRecords 
	 */
	private long iTotalDisplayRecords;

	/**
	 * <p>Description: 展示到table里的数据 </p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Fields aaData 
	 */
	private List<T> aaData;

	public DataTablesResponseEntity(){
		super();
	}

	public int getsEcho(){
		return sEcho;
	}

	public void setsEcho(int sEcho){
		this.sEcho = sEcho;
	}

	public long getiTotalRecords(){
		return iTotalRecords;
	}

	public void setiTotalRecords(long iTotalRecords){
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords(){
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords){
		if(iTotalDisplayRecords>10000){
			this.iTotalDisplayRecords = 10000;
		}else{
			this.iTotalDisplayRecords = iTotalDisplayRecords;
		}
		
	}

	public List<T> getAaData(){
		return aaData;
	}

	public void setAaData(List<T> list){
		this.aaData = list;
	}

}
