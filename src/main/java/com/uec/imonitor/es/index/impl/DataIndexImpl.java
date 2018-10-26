package com.uec.imonitor.es.index.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.uec.imonitor.common.base.BaseEntity;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.es.bean.EsClient;
import com.uec.imonitor.es.index.IDataIndex;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  es索引操作  </p> 
 * <p>Author:jlchen/陈金梁</p>
 * @param <T>
 */
@Service("dataIndex")
public class DataIndexImpl implements IDataIndex {
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EsClient esClient;


	/**
	 * <p>Description:插入索引对象内容</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: add
	 * @param indexName
	 * @param indexType
	 * @param entity
	 * @return
	 * @see com.uec.imonitor.es.index.IDataIndex#add(java.lang.String, java.lang.String, com.uec.imonitor.common.base.BaseEntity)
	 */
	@Override
	public  boolean add(String indexName, String indexType, BaseEntity  entity) {
		Client client = esClient.getTransportClient();
		if (null != client && null != entity) {
			String webpageJson = CommonUtil.toJson(entity);
			IndexResponse indexResponse = client.prepareIndex(indexName, indexType, 
					entity.getEsPrimaryId())
					.setSource(webpageJson).get();
			return indexResponse.isCreated();

		} else {
			return false;
		}
	}

	/**
	 * <p>Description:更新索引对象内容</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: update
	 * @param indexName
	 * @param indexType
	 * @param entity
	 * @return
	 * @see com.uec.imonitor.es.index.IDataIndex#update(java.lang.String, java.lang.String, com.uec.imonitor.common.base.BaseEntity)
	 */
	@Override
	public boolean update(String indexName, String indexType, BaseEntity entity) {
		Client client = esClient.getTransportClient();
		if (null != client && null != entity) {
			// ObjectMapper mapper = new ObjectMapper(); // jackson
			// String webpageJson = mapper.writeValueAsString(webpageDetail);
			String webpageJson = CommonUtil.toJson(entity);
			try {
				IndexRequest  indexResponse = new IndexRequest(indexName, indexType,
						entity.getEsPrimaryId()).source(webpageJson);
				UpdateRequest updateRequest = new UpdateRequest(indexName, indexType,
						entity.getEsPrimaryId()).doc(webpageJson).upsert(indexResponse);
				
				UpdateResponse updateResponse = client.update(updateRequest).get();
				logger.info("索引更新成功：_id = "+ updateResponse.getId() + ", primaryId= "+entity.getEsPrimaryId());
				return true;
			} catch (Exception e) {// 出现异常，使用add方式增加
				logger.error("索引更新失败, primaryId= "+entity.getEsPrimaryId(), e);
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * <p>Description:删除索引对象</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: delete
	 * @param indexName
	 * @param indexType
	 * @param primaryKey
	 * @return
	 * @see com.uec.imonitor.es.index.IDataIndex#delete(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean delete(String indexName, String indexType, String primaryKey) {
		Client client = esClient.getTransportClient();
		if (null != client && StringUtils.isNoneBlank(primaryKey)) {
			DeleteResponse deleteResponse = client.prepareDelete(indexName, indexType, 
					primaryKey).get();

			return deleteResponse.isFound();

		} else {
			return false;
		}
	}

	/**
	 * <p>Description:批量插入索引对象内容</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: bulkAdd
	 * @param indexName
	 * @param indexType
	 * @param entityList
	 * @return
	 * @see com.uec.imonitor.es.index.IDataIndex#bulkAdd(java.lang.String, java.lang.String, java.util.List)
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T extends BaseEntity> boolean bulkAdd(String indexName, String indexType, List<T> entityList) {
		Client client = esClient.getTransportClient();
		if (null != client && !CollectionUtils.isEmpty(entityList)) {
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			// 按插入进行记录操作
			for (BaseEntity hn : entityList) {
				String jsonlist = CommonUtil.toJson(hn);
				IndexRequestBuilder indexRequestBuilder = client.prepareIndex(indexName, indexType, 
						hn.getEsPrimaryId())
						.setSource(jsonlist);
				bulkRequest.add(indexRequestBuilder);
			}

			// either use client#prepare, or use Requests# to directly build
			// index/delete requests
			BulkResponse bulkResponse = bulkRequest.get();
			if (bulkResponse.hasFailures()) {
				logger.error("索引失败记录信息"+bulkResponse.buildFailureMessage());
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>Description:更新索引对象内容</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: update
	 * @param indexName
	 * @param indexType
	 * @param primaryKey
	 * @param updateMap
	 * @return
	 * @see com.uec.imonitor.es.index.IDataIndex#update(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public boolean update(String indexName, String indexType, String primaryKey, Map<String,String> updateMap) {
		Client client = esClient.getTransportClient();
		if (null != client && null != updateMap && !updateMap.isEmpty()) {
			String webpageJson = CommonUtil.toJson(updateMap);
			try {
				IndexRequest  indexResponse = new IndexRequest(indexName, indexType,
						primaryKey).source(webpageJson);
				UpdateRequest updateRequest = new UpdateRequest(indexName, indexType,
						primaryKey).doc(webpageJson).upsert(indexResponse);
				
				@SuppressWarnings("unused")
				UpdateResponse updateResponse = client.update(updateRequest).get();
				logger.info("索引更新成功：primaryKey = "+ primaryKey );
				return true;
			} catch (Exception e) {// 出现异常，使用add方式增加
				logger.error("索引更新失败，primaryKey =" +primaryKey, e);
				return false;
			}

		} else {
			return false;
		}

	}

	/**
	 * <p>Description:批量更新索引webpageDetail对象内容</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: bulkUpdate
	 * @param indexName
	 * @param indexType
	 * @param entityList
	 * @return
	 * @see com.uec.imonitor.es.index.IDataIndex#bulkUpdate(java.lang.String, java.lang.String, java.util.List)
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T extends BaseEntity> boolean bulkUpdate(String indexName, String indexType, List<T> entityList) {
		Client client = esClient.getTransportClient();
		if (null != client && !CollectionUtils.isEmpty(entityList)) {
			BulkRequestBuilder bulkRequest = client.prepareBulk();

			// 按更新进行记录操作
			for (BaseEntity hn : entityList) {
				// String webpageJson = mapper.writeValueAsString(hn);
				String webpageJson = CommonUtil.toJson(hn);
				IndexRequest indexRequest = new IndexRequest(indexName, indexType, 
						hn.getEsPrimaryId())
								.source(webpageJson);
				UpdateRequestBuilder updateRequestBuilder = client
						.prepareUpdate(indexName, indexType,
								hn.getEsPrimaryId())
						.setDoc(webpageJson).setUpsert(indexRequest);
				bulkRequest.add(updateRequestBuilder);

			}

			// either use client#prepare, or use Requests# to directly build
			// index/delete requests
			BulkResponse bulkResponse = bulkRequest.get();
			if (bulkResponse.hasFailures()) {
				logger.error("索引失败记录信息" + bulkResponse.buildFailureMessage());
				return false;
			} else {
				logger.info("更新索引成功");
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>Description:批量删除索引webpageDetail对象内容</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: bulkDelete
	 * @param indexName
	 * @param indexType
	 * @param primaryKeyList
	 * @return
	 * @see com.uec.imonitor.es.index.IDataIndex#bulkDelete(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public boolean bulkDelete(String indexName, String indexType, List<String> primaryKeyList) {
		Client client = esClient.getTransportClient();
		if (null != client && !CollectionUtils.isEmpty(primaryKeyList)) {
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			// 按插入进行记录操作
			for (String hn : primaryKeyList) {
				DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete(indexName, indexType, 
						hn);
				bulkRequest.add(deleteRequestBuilder);

			}

			// either use client#prepare, or use Requests# to directly build
			// index/delete requests
			BulkResponse bulkResponse = bulkRequest.get();
			if (bulkResponse.hasFailures()) {
				logger.error("索引失败记录信息"+bulkResponse.buildFailureMessage());
				return false;
			} else {
				return true;
			}
		}
		return false;
	}



}
