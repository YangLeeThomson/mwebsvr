package com.uec.imonitor.es.bean.params;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 排序参数</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class SortParams {
	private String sortField;//排序字段	
	private String sort;//排序方式，desc，降序、asc，升序
	
	public SortParams(){
		
	}
	
	public SortParams(String sortField,String sort){
		this.sort = sort;
		this.sortField = sortField;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
}
