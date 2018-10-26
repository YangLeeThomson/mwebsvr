package com.uec.imonitor.es.mapping;

import org.elasticsearch.common.xcontent.XContentBuilder;

import com.uec.imonitor.es.bean.index.IndexConfig;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  设置索引映射类型</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public interface IDataMapping {
	/**
	 * <br/>Description:索引映射配置，简化做法
	 * <p>Author:jlchen/陈金梁</p>
	 * @param iConfig
	 * @return
	 */
	public XContentBuilder mappingConfig(IndexConfig iConfig);
}
