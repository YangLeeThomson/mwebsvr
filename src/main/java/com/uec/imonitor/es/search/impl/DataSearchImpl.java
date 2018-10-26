package com.uec.imonitor.es.search.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.LinkedMap;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uec.imonitor.common.util.ESUtil;
import com.uec.imonitor.es.bean.params.AggsHistogramParams;
import com.uec.imonitor.es.bean.params.AggsTermParams;
import com.uec.imonitor.es.bean.params.QueryParams;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.es.search.IBaseSearch;
import com.uec.imonitor.es.search.IDataSearch;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  聚类结果全文检索</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
@Service("dataSearch")
public class DataSearchImpl implements IDataSearch {
	@Autowired
	private IBaseSearch baseSearch;
	/**
	 * <p>Description:全文检索</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @Title: textSearcher
	 * @param queryParams
	 * @return
	 * @see com.uec.IDataSearch.es.search.IClusterResultSearch#textSearcher(com.uec.inews.es.bean.params.QueryParams)
	 */
	@Override
	public <T> BaseQueryResult<T>  textSearcher(QueryParams<T> queryParams) {
		//最新新闻聚类检索参数
		AggsTermParams aggsParams = queryParams.getAggsParams();	
		//设置检索源index和类型type
		SearchResponse response = baseSearch.textSearcher(queryParams);
		//结果集
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		TimeValue timeTook = response.getTook();
		float maxScore = hits.getMaxScore();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<String, Long> aggsMap = new LinkedMap();
		//分组聚合
		if(null != aggsParams && null != response.getAggregations()){
			if(response.getAggregations() != null){
				Terms genders = response.getAggregations().get(aggsParams.getAggName());
				for (Terms.Bucket entry : genders.getBuckets()) {
				   String term =  entry.getKeyAsString();      // Term
				    Long count =entry.getDocCount(); // Doc count
				    aggsMap.put(term, count);
				}
			}
		}
		 Class<T> e = queryParams.getReturnClass();
		List<T> clusterResultList = ESUtil.parseESHits(hits, e);
		BaseQueryResult<T> result = new BaseQueryResult<T>( total, timeTook, maxScore);
		result.setResultList(clusterResultList);
		result.setAggsMap(aggsMap);
		return result;
	}
	
	/**
	 * <p>Description:短语检索</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @Title: phraseSearcher
	 * @param queryParams
	 * @return
	 * @see com.uec.IDataSearch.es.search.IClusterResultSearch#phraseSearcher(com.uec.inews.es.bean.params.QueryParams)
	 */
	@Override
	public <T> BaseQueryResult<T>  phraseSearcher(QueryParams<T> queryParams){
		//最新新闻聚类检索参数
		AggsTermParams aggsParams = queryParams.getAggsParams();	
		//设置检索源index和类型type
		SearchResponse response = baseSearch.phraseSearcher(queryParams);

		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		TimeValue timeTook = response.getTook();
		float maxScore = hits.getMaxScore();
		//分组聚合
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<String, Long> aggsMap = new LinkedMap();
		if(null != aggsParams && null != response.getAggregations()){
			Terms genders = response.getAggregations().get(aggsParams.getAggName());
			for (Terms.Bucket entry : genders.getBuckets()) {
			   String term =  entry.getKeyAsString();      // Term
			    Long count =entry.getDocCount(); // Doc count
			    aggsMap.put(term, count);
			}
		}
		 Class<T> e = queryParams.getReturnClass();
		List<T> objList = ESUtil.parseESHits(hits, e);
		BaseQueryResult<T> result = new BaseQueryResult<T>( total, timeTook, maxScore);
		result.setResultList(objList);
		result.setAggsMap(aggsMap);
		return result;
	}
	
	@Override
	public <T> BaseQueryResult<T> textHistogramSearcher(QueryParams<T> queryParams) {
		//设置检索源index和类型type
		SearchRequestBuilder srb = baseSearch.getSearchRequestBuilder(queryParams);
		SearchResponse response = srb.execute().actionGet();

		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		TimeValue timeTook = response.getTook();
		float maxScore = hits.getMaxScore();
		Map<String, Long> aggsMap = new LinkedMap<String, Long>();

		// 最新新闻聚类检索参数
		AggsHistogramParams histogram = queryParams.getHistogramParams();

		if (histogram != null && response.getAggregations() != null) {
			Histogram genders = response.getAggregations().get(histogram.getAggsName());
			for (Histogram.Bucket entry : genders.getBuckets()) {
				String term = entry.getKeyAsString(); // Term
				Long count = entry.getDocCount(); // Doc count
//				System.out.println(term + ", " + count); 
				aggsMap.put(term, count);
			}
		}

		Class<T> e = queryParams.getReturnClass();
		List<T> objList = ESUtil.parseESHits(hits, e);
		BaseQueryResult<T> result = new BaseQueryResult<T>(total, timeTook, maxScore);
		result.setAggsMap(aggsMap);
		result.setResultList(objList);

		return result;
	}



	/**
	 * <br/>Description: 获取所有最新新闻索引信息
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @return
	 */
	@Override
	public <T> BaseQueryResult<T> matchAllSearch(QueryParams<T> queryParams) {
//		Client client = ESUtil.getESClient(); // es连接
		//设置检索源index和类型type
		SearchResponse response  = baseSearch.matchAllSearch(queryParams);

		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		TimeValue timeTook = response.getTook();
		float maxScore = hits.getMaxScore();
	
		Class<T> e = queryParams.getReturnClass();
		List<T> objList = ESUtil.parseESHits(hits, e);
		BaseQueryResult<T> result = new BaseQueryResult<T>(total, timeTook, maxScore);
		result.setResultList(objList);

		return result;
	}
}
