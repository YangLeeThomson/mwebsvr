package com.uec.imonitor.es.bean.params;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  使用range匹配的参数集</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class RangeParams {
	private String field;//字段名
	private Object start;//区间开始值
	private Object end; //区间结束值
	
	/**
	 * <br/>Description:构造函数，默认字段间采用and连接
	 * <p>Author:jlchen/陈金梁</p>
	 * @param field 字段名
	 * @param start 区间开始值
	 * @param end 区间结束值
	 */
	public RangeParams(String field,Object start,Object end){
		this.field = field;
		this.start = start;
		this.end = end;
	}
	

	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}

	public Object getStart() {
		return start;
	}

	public void setStart(Object start) {
		this.start = start;
	}

	public Object getEnd() {
		return end;
	}

	public void setEnd(Object end) {
		this.end = end;
	}


}
