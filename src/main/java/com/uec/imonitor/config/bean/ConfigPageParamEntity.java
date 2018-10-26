package com.uec.imonitor.config.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 页面参数配置实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Entity
@Table(name="config_page_params")
public class ConfigPageParamEntity {
	/**
	 * 主键
	 */
	@Id//指明这个属性映射为数据库的主键
	@GeneratedValue
	@Column(name="param_id",nullable=false)
	private Integer paramId;   //参数id
	
	@Column(name="name")
	private String name;    //英文名称，唯一
	
	@Column(name="display_name")
	private String displayName;  //中文显示名称
	 
	@Column(name="value")
	private String value;  //参数值
	
	@Column(name="description")
	private String description;  //描述

	public Integer getParamId() {
		return paramId;
	}

	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	 
	
}
