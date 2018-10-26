package com.uec.imonitor.es.bean.params;

import java.util.List;

import org.elasticsearch.index.query.MatchQueryBuilder.Type;;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 使用match匹配的参数集 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class MatchParams {
	private List<String> fieldList;//字段名
	private List<String> valueList;//字段值
	private String anaylzer;//分词方式
	private String inOpt;//matchParams内部，多个检索字段间的操作方式，and、or
	private String outOpt;//各个matchParams之间连接符，and、or、not、filter
	private String fieldOpt;//field分词后，各个词汇间的连接方式及同一字段内，各个value操作方式，and、or
	private Type type;//类型，短语phrase,布尔boolean（默认）
	
	public MatchParams(){
	}
	/**
	 * <br/>Description: 构造函数，默认字段内采用or连接，字段间采用and连接
	 * <p>Author:jlchen/陈金梁</p>
	 * @param fieldList
	 * @param valueList
	 */
	public MatchParams(List<String> fieldList,List<String> valueList){
		this.fieldList = fieldList;
		this.valueList = valueList;		
	}
	/**
	 * <br/>Description:构造函数
	 * <p>Author:jlchen/陈金梁</p>
	 * @param fieldList
	 * @param valueList
	 * @param inOpt
	 * @param outOpt
	 * @param analyzer
	 */
	public MatchParams(List<String> fieldList,List<String> valueList, String inOpt, String outOpt, String analyzer){
		this.fieldList = fieldList;
		this.valueList = valueList;
		this.inOpt = inOpt;
		this.outOpt = outOpt;
		this.anaylzer = analyzer;
	}

	/**
	 * <br/>Description:构造函数
	 * <p>Author:jlchen/陈金梁</p>
	 * @param fieldList
	 * @param valueList
	 * @param inOpt
	 * @param outOpt
	 * @param fieldOpt
	 * @param analyzer
	 */
	public MatchParams(List<String> fieldList,List<String> valueList, String inOpt, String outOpt, String fieldOpt, String analyzer){
		this.fieldList = fieldList;
		this.valueList = valueList;
		this.inOpt = inOpt;
		this.outOpt = outOpt;
		this.fieldOpt = fieldOpt;
		this.anaylzer = analyzer;
	}
	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public List<String> getValueList() {
		return valueList;
	}

	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}

	public String getInOpt() {
		return inOpt;
	}

	public void setInOpt(String inOpt) {
		this.inOpt = inOpt;
	}

	public String getOutOpt() {
		return outOpt;
	}

	public void setOutOpt(String outOpt) {
		this.outOpt = outOpt;
	}

	public String getAnaylzer() {
		return anaylzer;
	}

	public void setAnaylzer(String anaylzer) {
		this.anaylzer = anaylzer;
	}

	public String getFieldOpt() {
		return fieldOpt;
	}

	public void setFieldOpt(String fieldOpt) {
		this.fieldOpt = fieldOpt;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
}
