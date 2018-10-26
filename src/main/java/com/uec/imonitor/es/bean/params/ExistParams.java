package com.uec.imonitor.es.bean.params;

import java.util.List;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 使用exits匹配的参数集 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class ExistParams {
	private List<String> fieldList;//字段名
	
	public ExistParams(List<String> fieldList){
		this.fieldList = fieldList;
	}

	public ExistParams(){
	}
	
	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

}
