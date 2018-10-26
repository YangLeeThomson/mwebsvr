package com.uec.imonitor.es.service;

import java.util.List;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.es.bean.NewsStatusEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 记录状态服务层 </p> 
 * <p>Author:cjl/陈金梁</p>
 */
public interface INewsStatusService {
	
	/**
	 * <br/>Description:新增
	 * <p>Author:jlchen/陈金梁</p>
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public NewsStatusEntity add(NewsStatusEntity entity) throws BaseException, Exception;
	
	/**
	 * <br/>Description:更新
	 * <p>Author:jlchen/陈金梁</p>
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public NewsStatusEntity addOrUpdate(NewsStatusEntity entity) throws BaseException, Exception;

	/**
	 * <br/>Description:通过表名获取所有变化的数据
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param tableName
	 * @return
	 * @throws RequestParamException 
	 */
	public List<NewsStatusEntity> listByTableName(String tableName) throws RequestParamException;
	
	/**
	 * <br/>Description:通过表名获取TopN数据
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 */
	public List<NewsStatusEntity> listByTableName(String tableName , int topN) throws RequestParamException;
	
	/**
	 * <br/>Description:通过表名获取插入的数据
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param tableName
	 * @return
	 * @throws RequestParamException 
	 */
	public List<NewsStatusEntity> listInsertRecordsByTableName(String tableName) throws RequestParamException;
	
	/**
	 * <br/>Description:通过表名获取topN的插入数据
	 * <p>Author:jlchen/陈金梁</p>
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 */
	public List<NewsStatusEntity> listTopNInsertRecordsByTableName(String tableName,int topN) throws RequestParamException;
	/**
	 * <br/>Description:通过表名获取topN的新增和更新的数据
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 */
	public List<NewsStatusEntity> listTopInsertAndUpdateByTable(String tableName, int topN) throws RequestParamException;
	
	/**
	 * <br/>Description:通过表名获取topN的update的数据
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 */
	public List<NewsStatusEntity> listTopNUpdateRecordsByTableName(String tableName, int topN) throws RequestParamException;
	
	/**
	 * <br/>Description:通过表名获取所有变化的数据
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param tableName
	 * @return
	 * @throws RequestParamException 
	 */
	public List<NewsStatusEntity> listUpdateRecordsByTableName(String tableName) throws RequestParamException;
	
	
	
	/**
	 * <br/>Description:通过表名获取topN的deleted的数据
	 * <p>Author:jlchen/陈金梁</p>
	 * @param tableName
	 * @param topN
	 * @return
	 * @throws RequestParamException 
	 */
	public List<NewsStatusEntity> listTopNDeletedRecordsByTableName(String tableName,int topN) throws RequestParamException;
	/**
	 * <br/>Description:通过id删除数据
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param tableName
	 * @return
	 */
	public Boolean deleteEsStatus(int innerid);
	
	/**
	 * <br/>Description:通过ids删除数据
	 * <p>Author:jinlChen/陈金梁</p>
	 * @param tableName
	 * @return
	 * @throws RequestParamException 
	 */
	public Boolean deleteEsStatusList(List<NewsStatusEntity> esStatusList) throws RequestParamException;
	

}
