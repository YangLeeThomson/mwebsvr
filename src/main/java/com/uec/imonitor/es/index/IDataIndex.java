package com.uec.imonitor.es.index;

import java.util.List;
import java.util.Map;

import com.uec.imonitor.common.base.BaseEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: es索引操作 </p> 
 * <p>Author:cjl/陈金梁</p>
 * @param <T>
 */
public interface IDataIndex {	

	/**
	 * <br/>Description:插入索引对象内容
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @param indexName
	 * @param indexType
	 * @param entity
	 * @return
	 */
	public <T extends BaseEntity> boolean add(String indexName, String indexType, T entity);

	/**
	 * <br/>Description:更新索引对象内容
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @param indexName
	 * @param indexType
	 * @param entity
	 * @return
	 */
	public <T extends BaseEntity> boolean update(String indexName, String indexType, T entity);
	
	/**
	 * <br/>Description:删除索引对象
	 * <p>Author:jlchen/陈金梁</p>
	 * @param indexName
	 * @param indexType
	 * @param primaryKey
	 * @return
	 */
	public <T extends BaseEntity> boolean delete(String indexName, String indexType, String primaryKey);
	/**
	 * <br/>Description:批量插入索引对象内容
	 * <p>Author:jlchen/陈金梁</p>
	 * @param indexName
	 * @param indexType
	 * @param entityList
	 * @return
	 */
	public <T extends BaseEntity> boolean bulkAdd(String indexName, String indexType, List<T> entityList);
	
	/**
	 * <br/>Description:更新索引对象内容
	 * <p>Author:jlchen/陈金梁</p>
	 * @param indexName
	 * @param indexType
	 * @param primaryKey
	 * @param updateMap
	 * @return
	 */
	public boolean update(String indexName, String indexType, String primaryKey, Map<String,String> updateMap);
	/**
	 * <br/>Description:批量更新索引webpageDetail对象内容
	 * <p>Author:jlchen/陈金梁</p>
	 * @param indexName
	 * @param indexType
	 * @param entityList
	 * @return
	 */
	public <T extends BaseEntity> boolean bulkUpdate(String indexName, String indexType, List<T> entityList);
	
	/**
	 * <br/>Description:批量删除索引webpageDetail对象内容
	 * <p>Author:jlchen/陈金梁</p>
	 * @param indexName
	 * @param indexType
	 * @param primaryKeyList
	 * @return
	 */
	public boolean bulkDelete(String indexName, String indexType, List<String> primaryKeyList);

}
