package com.uec.imonitor.es.index.impl;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uec.imonitor.es.bean.EsClient;
import com.uec.imonitor.es.bean.index.IndexConfig;
import com.uec.imonitor.es.index.IEsIndex;
import com.uec.imonitor.es.mapping.IDataMapping;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: ES创建索引  </p> 
 * <p>Author:jlchen/陈金梁</p>
 */
@Service("esIndex")
public class EsIndexImpl implements IEsIndex {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EsClient esClient;
	@Autowired
	@Qualifier("proxyMapping")
	private IDataMapping proxyMapping;

	@Override
	public boolean createIndex(IndexConfig iConfig) {
		if (iConfig == null) {
			logger.error("索引配置参数为空");
			return false;
		} else if (iConfig.getIndexName() == null) {
			logger.error("索引名称参数为空");
			return false;
		} else if (iConfig.getTypeName() == null) {
			logger.error("索引type参数为空");
			return false;
		} else {
			String indexName = iConfig.getIndexName();
			String typeName = iConfig.getTypeName();
			if(this.isExistsIndex(indexName)){
				logger.info("索引名称为"+indexName+"的索引已存在");
				if(this.isExistsType(indexName, typeName)){
					logger.info("名称为"+indexName+"，类型名为"+typeName+"的索引已存在");
					return true;
				}else{
					return doIndexMapping(iConfig);
				}
				
			}else {
				return doIndexMapping(iConfig);
				
			}
			

		}
	}

	/**
	 * <br/>Description:通过配置文件，生成mapping
	 * <p>Author:jlchen/陈金梁</p>
	 * @param iConfig
	 * @return
	 */
	private boolean doIndexMapping(IndexConfig iConfig) {
		XContentBuilder indexBuilder = null;
		Client client = esClient.getTransportClient();
		try {
			indexBuilder = XContentFactory.jsonBuilder().startObject().startObject("settings")
					.field("number_of_shards", iConfig.getShardsNum())// 设置分片数量
					.field("number_of_replicas", iConfig.getReplicasNum())// 设置副本数量
					.endObject().endObject();
		} catch (IOException e) {
			logger.error("es索引构建时，json转换异常：" + e);

		}
		if(indexBuilder == null){
			return false;
		}else{
			CreateIndexRequestBuilder cirb = client.admin().indices().prepareCreate(iConfig.getIndexName())// index名称
					.setSource(indexBuilder);
			XContentBuilder mappingBulider = proxyMapping.mappingConfig(iConfig);
			cirb.addMapping(iConfig.getTypeName(),mappingBulider); //mapping设置
			CreateIndexResponse response = cirb.execute().actionGet();
			if (response.isAcknowledged()) {
				logger.info("名为" + iConfig.getIndexName() + "的索引创建成功");
				System.out.println("名为" + iConfig.getIndexName() + "的索引创建成功！");
				return true;
			} else {
				logger.info("名为" + iConfig.getIndexName() + "的索引创建失败");
				System.err.println("名为" + iConfig.getIndexName() + "的索引创建失败！");
				return false;
			}
		}
	}

	@Override
	public boolean isExistsIndex(String indexName) {
		if (indexName == null) {
			logger.error("索引库名称参数为空");
			return false;
		} else {
//			Client client = ESUtil.getESClient();
			Client client = esClient.getTransportClient();
			IndicesExistsResponse response = client.admin().indices()
					.exists(new IndicesExistsRequest().indices(new String[] { indexName })).actionGet();
			boolean flag = response.isExists();
			logger.info("es集群中索引名称为" + indexName + "的index是否存在：" + flag);
			return flag;
		}

	}

	@Override
	public boolean isExistsType(String indexName, String indexType) {
		if (indexName == null) {
			logger.error("索引库名称参数为空");
			return false;
		} else if (indexType == null) {
			logger.error("索引库type参数为空");
			return false;
		} else {
//			Client client = ESUtil.getESClient();
			Client client = esClient.getTransportClient();
			TypesExistsResponse response = client.admin().indices()
					.typesExists(new TypesExistsRequest(new String[] { indexName }, indexType)).actionGet();
			boolean flag = response.isExists();
			logger.info("es集群中索引名称为" + indexName + "，type类型为" + indexType + "的index是否存在：" + flag);
			return flag;
		}

	}

}
