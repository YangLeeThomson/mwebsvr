package com.uec.imonitor.es.index;

import com.uec.imonitor.es.bean.index.IndexConfig;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: ES创建索引 </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public interface IEsIndex {
	
	/**
	 * <br/>Description:创建索引
	 * <p>Author:jlchen/陈金梁</p>
	 * @param client
	 * @param iConfig
	 * @return
	 */
	public boolean createIndex(IndexConfig iConfig);
	
	/**
	 * <br/>Description:判断索引是否存在
	 * <p>Author:jlchen/陈金梁</p>
	 * @param client
	 * @param indexName
	 * @return
	 */
	public boolean isExistsIndex(String indexName);
	
	/**
	 * <br/>Description:判断索引中某个类型是否存在
	 * <p>Author:jlchen/陈金梁</p>
	 * @param client
	 * @param indexName
	 * @param indexType
	 * @return
	 */
	public boolean isExistsType(String indexName,String indexType);
}
