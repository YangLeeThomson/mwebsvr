package com.uec.imonitor.es.bean.params;

import java.util.List;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 基础查询参数</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class BaseQueryParams {
	private String[] indexArray; // 索引库集
	private  String[] typeArray;// 类型集
	
	private int start;// 起始条
	private int size; // 条数
	private List<SortParams> sortList;//排序字段
	
	/**
	 * <br/>Description:无参构造函数
	 * <p>Author:jlchen/陈金梁</p>
	 */
	public BaseQueryParams(){
		
	}
	
	/**
	 * <br/>Description: 带参构造函数
	 * <p>Author:jlchen/陈金梁</p>
	 * @param start 起始条
	 * @param size 返回条数
	 * @param sortField 排序字段
	 * @param sort 排序方式，desc，降序、asc，升序
	 */
	public BaseQueryParams(int start,int size
			,List<SortParams> sortList){
		this.size = size;
		this.start =start;
		this.sortList =  sortList;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public List<SortParams> getSortList() {
		return sortList;
	}
	public void setSortList(List<SortParams> sortList) {
		this.sortList = sortList;
	}
	public String[] getIndexArray() {
		return indexArray;
	}
	public void setIndexArray(String[] indexArray) {
		this.indexArray = indexArray;
	}
	public String[] getTypeArray() {
		return typeArray;
	}
	public void setTypeArray(String[] typeArray) {
		this.typeArray = typeArray;
	}
	
}
