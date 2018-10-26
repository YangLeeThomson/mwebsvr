package com.uec.imonitor.es.bean.params;

import com.uec.imonitor.common.util.ESConstantUtil;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  使用Term aggregation聚合匹配的参数集</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class AggsTermParams {
	private String aggName; //聚合查询名称
	private String field;//字段名
	private String sort; //排序
	private int size; //取多少个聚合结果
	
	/**
	 * <br/>Description: 聚合构造函数，默认使用降序排列
	 * <p>Author:jlchen/陈金梁</p>
	 * @param aggName 聚合查询名称
	 * @param field 聚合字段
	 */
	public AggsTermParams(String aggName,String field){
		this.aggName = aggName;
		this.field = field;
		this.size = ESConstantUtil.DEFAULT_AGGS_NUM;
		this.sort = ESConstantUtil.SORT_DESC;
	}

	/**
	 * <br/>Description:聚合参数构造函数
	 * <p>Author:jlchen/陈金梁</p>
	 * @param aggName 聚合查询名称
	 * @param field 聚合字段名
	 * @param sort 聚合结果排序方式
	 * @param size 聚合结果展示个数
	 */
	public AggsTermParams(String aggName, String field,String sort, int size){
		this.aggName = aggName;
		this.field = field;
		this.sort = sort;
		this.size = size;
	}
	
	public String getAggName() {
		return aggName;
	}

	public void setAggName(String aggName) {
		this.aggName = aggName;
	}

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
}
