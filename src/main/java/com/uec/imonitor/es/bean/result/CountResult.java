package com.uec.imonitor.es.bean.result;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 索引结果统计 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class CountResult {
	private int today_num;//今天更新索引数
	private int total_num;//总共索引记录数

	public CountResult(){
		
	}
	/**
	 * <br/>Description: 
	 * <p>Author:jlchen/陈金梁</p>
	 * @param today_num 今天更新索引数
	 * @param total_num 总共索引记录数
	 */
	public CountResult(int today_num,int total_num){
		this.today_num = today_num;
		this.total_num = total_num;
	}

	public int getToday_num() {
		return today_num;
	}

	public void setToday_num(int today_num) {
		this.today_num = today_num;
	}

	public int getTotal_num() {
		return total_num;
	}

	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}
	
	
}
