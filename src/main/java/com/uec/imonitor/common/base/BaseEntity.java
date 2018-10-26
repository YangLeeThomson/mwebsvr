package com.uec.imonitor.common.base;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: Entity基础类 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public class BaseEntity {

	/**
	 * <p>Description:es索引主键 </p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Fields esPrimartyId 
	 */
	private String esPrimaryId;

	/**
	 * <br/>Description:获取es索引主键
	 * <p>Author:jlchen/陈金梁</p>
	 * @return
	 */
	public String getEsPrimaryId() {
		return esPrimaryId;
	}
	/**
	 * <br/>Description:设置es索引主键
	 * <p>Author:jlchen/陈金梁</p>
	 * @param esPrimartyId
	 */
	public void setEsPrimaryId(String esPrimaryId) {
		this.esPrimaryId = esPrimaryId;
	}
	
	
}
