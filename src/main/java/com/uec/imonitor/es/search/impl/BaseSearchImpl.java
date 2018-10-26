package com.uec.imonitor.es.search.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.elasticsearch.index.query.MatchQueryBuilder.Type;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.uec.imonitor.common.util.ESConstantUtil;
import com.uec.imonitor.es.bean.EsClient;
import com.uec.imonitor.es.bean.params.AggsHistogramParams;
import com.uec.imonitor.es.bean.params.AggsTermParams;
import com.uec.imonitor.es.bean.params.ExistParams;
import com.uec.imonitor.es.bean.params.MatchParams;
import com.uec.imonitor.es.bean.params.QueryParams;
import com.uec.imonitor.es.bean.params.RangeParams;
import com.uec.imonitor.es.bean.params.SortParams;
import com.uec.imonitor.es.search.IBaseSearch;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  ES基础检索方法封装</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
@Service(value = "baseSearch")
public class BaseSearchImpl implements IBaseSearch {
	@Autowired
	private EsClient esClient;
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	/**
	 * <br/>Description: 全文检索
	 * <p>Author:jlchen/陈金梁</p>
	 * @param queryParams
	 * @return
	 */
	@Override
	public <T> SearchResponse textSearcher(QueryParams<T> queryParams) {
		long startTime=System.currentTimeMillis();   //获取开始时间
		
		SearchRequestBuilder srb = this.getSearchRequestBuilder(queryParams);
		SearchResponse response = srb.execute().actionGet();

		long endTime=System.currentTimeMillis(); //获取结束时间
		logger.info("检索程序运行时间： "+(endTime-startTime)+"ms");
		
		return response;
	}

	@Override
	public <T> SearchRequestBuilder getSearchRequestBuilder(QueryParams<T> queryParams) {
		String[] indexArray = queryParams.getIndexArray();
		String[] typeArray = queryParams.getTypeArray();
		Client client = esClient.getTransportClient();
		SearchRequestBuilder srb = client.prepareSearch(indexArray).setTypes(typeArray);
		//
		List<MatchParams> matchList = queryParams.getMatchList();
//		srb.addFields(queryParams.getIndexArray());
		BoolQueryBuilder bqb =  QueryBuilders.boolQuery();
		//直接利用查询参数进行查询设置
		if(!CollectionUtils.isEmpty(matchList)){
			for(MatchParams mp : matchList){
				String out_opt = mp.getOutOpt();//各个matchParams之间连接符，and、or、not、filter
				BoolQueryBuilder mpbqb =transMatchParamsToQueryBuilder(mp);
				//字段间连接方式
				if(ESConstantUtil.OPT_OR.equals(out_opt)){
					bqb.should(mpbqb);
				}else if(ESConstantUtil.OPT_NOT.equals(out_opt)){
					bqb.mustNot(mpbqb);
				}else if(ESConstantUtil.OPT_FILTER.equals(out_opt)){//过滤查询
					bqb.filter(mpbqb);
				}else{//默认and连接
					bqb.must(mpbqb);
				}
			}
		}
		
		//字段是否存在检索
		ExistParams exitParams = queryParams.getExistParams();
		if(null != exitParams){
			List<String> fieldList = exitParams.getFieldList();
			if(!CollectionUtils.isEmpty(fieldList)){
				for(String field : fieldList){
					QueryBuilder qb = QueryBuilders.existsQuery(field);
					bqb.must(qb);
				}
			}
		}
		
		//区间过滤
		List<RangeParams> rangeList = queryParams.getRangeList();
		if(!CollectionUtils.isEmpty(rangeList)){
			for(RangeParams rp : rangeList){
				QueryBuilder qb= QueryBuilders.rangeQuery(rp.getField()).from(rp.getStart()).to(rp.getEnd());
				bqb.must(qb);
			}
			
		}
		srb.setQuery(bqb);
		
		// id集合过滤
		String[] idsList = queryParams.getIds();
		if (null != idsList && idsList.length > 0) {
			QueryBuilder qb = QueryBuilders.idsQuery().addIds(idsList);
			bqb.must(qb);
		}
				
		//返回字段集
//		String[] returnFieldsArray = queryParams.getReturnFieldsArray();
//		if(returnFieldsArray != null && returnFieldsArray.length >0){
//			srb.addFields(returnFieldsArray);
//		}
		
		//返回值范围--深度分页问题，此处得细化，10000
		if (queryParams.getStart() >= 0 && queryParams.getSize() > 0 && (queryParams.getStart()< 10000)) {
			if(queryParams.getStart() + queryParams.getSize() <=10000){
				srb.setFrom(queryParams.getStart()).setSize(queryParams.getSize());
			}else{
				srb.setFrom(queryParams.getStart()).setSize(10000 - queryParams.getStart());
			}
			
		}
		//聚合检索
		AggsTermParams aggsParam = queryParams.getAggsParams();
		AggsHistogramParams histogram = queryParams.getHistogramParams();
		if(null != aggsParam){//term聚合检索
			String aggs_name = aggsParam.getAggName();
			String field = aggsParam.getField();
			Integer size = aggsParam.getSize();
			String sort = aggsParam.getSort();
			if(null !=size && size>0){
				if(ESConstantUtil.SORT_ASC.equals(sort.toLowerCase())){
					TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).size(size).order(Terms.Order.count(true));
					srb.addAggregation(aggs);
				}else{
					TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).size(size).order(Terms.Order.count(false));
					srb.addAggregation(aggs);
				}
				
			}else{
				if(ESConstantUtil.SORT_ASC.equals(sort.toLowerCase())){
					TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).order(Terms.Order.count(true));
					srb.addAggregation(aggs);
				}else{
					TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).order(Terms.Order.count(false));
					srb.addAggregation(aggs);
				}
				
			}
		}else if(null != histogram){
			//histogram 聚合检索
			String aggs_name2 = histogram.getAggsName();
			String field2 = histogram.getAggsField();
			String timeZone = histogram.getTimeZone();
			String format = histogram.getFormat();
			
			Date minDate = histogram.getMinDate();
			Date maxDate = histogram.getMaxDate();
			Long min = null, max = null ;
			if(null != minDate){
				min = minDate.getTime();
			}
			if(null != maxDate){
				max = maxDate.getTime();
			}
			DateHistogramInterval interval = histogram.getInterval();
			@SuppressWarnings("rawtypes")
			AggregationBuilder aggregation =
			        AggregationBuilders
			                .dateHistogram(aggs_name2)
			                .field(field2)
			                .timeZone(timeZone)
			                .format(format).extendedBounds(min, max)
			                .interval(interval);
			
			srb.addAggregation(aggregation);
		}
				
		//检索排序
		List<SortParams> sortList = queryParams.getSortList();
		if(null != sortList){
			for(SortParams sp : sortList){
				String sortField = sp.getSortField();
				String sort = sp.getSort();
				if(ESConstantUtil.SORT_ASC.equals(sort)){
					srb.addSort(sortField, SortOrder.ASC);
				}else{
					srb.addSort(sortField, SortOrder.DESC);
				}
			}
		}
			
		return srb.setExplain(false);
	}

	/**
	 * <br/>Description:将MatchParams参数转化为queryBuilder
	 * <p>Author:jlchen/陈金梁</p>
	 * @param mp
	 * @return
	 */
	private  BoolQueryBuilder transMatchParamsToQueryBuilder(MatchParams mp) {
		BoolQueryBuilder mpbqb = QueryBuilders.boolQuery();
		List<String> fieldList = mp.getFieldList();
		List<String> valueList = mp.getValueList();
		
		String in_opt = mp.getInOpt();//matchParams内部，各个字段,各个value操作方式，and、or
		
		String field_opt = mp.getFieldOpt();//field分词后，各个词汇间的连接方式；and、or、not
		//设置默认连接符
		Operator fieldOpt = Operator.AND;//field分词后词汇间默认采用and连接
		if(ESConstantUtil.OPT_OR.equals(field_opt)){//field分词后词汇间采用连接
			fieldOpt = Operator.OR;
		}
		
		for(int i = 0 ; fieldList!= null && i < fieldList.size(); i++){
			String field = fieldList.get(i);
			for(int j = 0; valueList != null && j < valueList.size(); j ++){
				String value = valueList.get(j);
				String analyzer = mp.getAnaylzer();
				QueryBuilder qb;
				if(ESConstantUtil.ANALYZER_IK_SMART.equals(analyzer)){
					if(mp.getType() == null){
						qb = QueryBuilders.matchQuery(field, value).operator(fieldOpt)
								.analyzer(analyzer);
					}else{
						qb = QueryBuilders.matchQuery(field, value).type(mp.getType())
								.analyzer(analyzer);
					}
				}else if(analyzer != null && analyzer.equals(ESConstantUtil.ANALYZER_NOT)){
						qb = QueryBuilders.matchQuery(field, value);
				}else{
					if(mp.getType() == null){
						qb = QueryBuilders.matchQuery(field, value).operator(fieldOpt)
								.analyzer(ESConstantUtil.ANALYZER_IK_MAX);
					}else{
						qb = QueryBuilders.matchQuery(field, value).type(mp.getType()).analyzer(analyzer);
					}
				}
				//matchParams内部，各个字段,各个value操作方式，and、or
				if(ESConstantUtil.OPT_OR.equals(in_opt)){
					mpbqb.should(qb);
				}else if(ESConstantUtil.OPT_NOT.equals(in_opt)){
					mpbqb.mustNot(qb);
				}else if(ESConstantUtil.OPT_FILTER.equals(in_opt)){//过滤查询
					mpbqb.filter(qb);
				}else{//默认and连接
					mpbqb.must(qb);
				}
			}
		}
		return mpbqb;
	}
	
	

	@Override
	public <T> SearchResponse matchAllSearch(QueryParams<T> queryParams) {
		String[] indexArray = queryParams.getIndexArray();
		String[] typeArray = queryParams.getTypeArray();
		Client client = esClient.getTransportClient();
		SearchRequestBuilder srb = client.prepareSearch(indexArray).setTypes(typeArray);
		QueryBuilder qb = QueryBuilders.matchAllQuery();
		srb.setQuery(qb);
		srb.setExplain(false);
		
		SearchResponse response = srb.execute().actionGet();
		return response;
	}

	/**
	 * <p>Description:短语检索</p>
	 * <p>Author:jlchen/陈金梁</p>
	 * @Title: phraseSearcher
	 * @param queryParams
	 * @return
	 * @see com.uec.inews.es.search.IBaseSearch#phraseSearcher(com.uec.inews.es.bean.params.QueryParams)
	 */
	public  <T> SearchResponse phraseSearcher(QueryParams<T> queryParams) {
		long startTime=System.currentTimeMillis();   //获取开始时间
		
		SearchRequestBuilder srb = this.getPhraseRequestBuilder(queryParams);
		SearchResponse response = srb.execute().actionGet();

		long endTime=System.currentTimeMillis(); //获取结束时间
		logger.info("检索程序运行时间： "+(endTime-startTime)+"ms");
		
		return response;
	}
	
	/**
	 * <br/>Description:检索式解析
	 * <p>Author:jlchen/陈金梁</p>
	 * @param queryParams
	 * @return
	 */
	public <T> SearchRequestBuilder getPhraseRequestBuilder(QueryParams<T> queryParams){
		String[] indexArray = queryParams.getIndexArray();
		String[] typeArray = queryParams.getTypeArray();
		Client client = esClient.getTransportClient();
		SearchRequestBuilder srb = client.prepareSearch(indexArray).setTypes(typeArray);
		//
		List<MatchParams> matchList = queryParams.getMatchList();
//		srb.addFields(queryParams.getIndexArray());
		BoolQueryBuilder bqb =  QueryBuilders.boolQuery();
		//直接利用查询参数进行查询设置
		if(!CollectionUtils.isEmpty(matchList)){
			List<MatchParams> phraseList = new ArrayList<MatchParams>();
			List<MatchParams> filterList = new  ArrayList<>();
			for(MatchParams mp : matchList){//对检索条件分组
				Type type = mp.getType();
				if(Type.PHRASE.equals(type)){//短语检索部分
					phraseList.add(mp);
				}else{//其他
					filterList.add(mp);
				}
			}
			//短语检索检索式解析
			BoolQueryBuilder mpbqbPhrase = new BoolQueryBuilder();
			for(MatchParams mp : phraseList){
				String in_opt = mp.getInOpt();//matchParams内部，多个检索字段间的操作方式，and、or
				BoolQueryBuilder mpbqb =transMatchParamsToPhraseQueryBuilder(mp);
				//字段间连接方式
				if(ESConstantUtil.OPT_OR.equals(in_opt)){
					mpbqbPhrase.should(mpbqb);
				}else if(ESConstantUtil.OPT_NOT.equals(in_opt)){
					mpbqbPhrase.mustNot(mpbqb);
				}else{//默认and连接
					mpbqbPhrase.must(mpbqb);
				}
			}
			bqb.must(mpbqbPhrase);
			//其他检索式解析
			for(MatchParams mp : filterList){
				String out_opt = mp.getOutOpt();//各个matchParams之间连接符，and、or、not、filter
				BoolQueryBuilder mpbqb =transMatchParamsToQueryBuilder(mp);
				//字段间连接方式
				if(ESConstantUtil.OPT_OR.equals(out_opt)){
					bqb.should(mpbqb);
				}else if(ESConstantUtil.OPT_NOT.equals(out_opt)){
					bqb.mustNot(mpbqb);
				}else if(ESConstantUtil.OPT_FILTER.equals(out_opt)){//过滤查询
					bqb.filter(mpbqb);
				}else{//默认and连接
					bqb.must(mpbqb);
				}
			}
		}

		
		//字段是否存在检索
		ExistParams exitParams = queryParams.getExistParams();
		if(null != exitParams){
			List<String> fieldList = exitParams.getFieldList();
			if(!CollectionUtils.isEmpty(fieldList)){
				for(String field : fieldList){
					QueryBuilder qb = QueryBuilders.existsQuery(field);
					bqb.must(qb);
				}
			}
		}
		
		//区间过滤
		List<RangeParams> rangeList = queryParams.getRangeList();
		if(!CollectionUtils.isEmpty(rangeList)){
			for(RangeParams rp : rangeList){
				QueryBuilder qb= QueryBuilders.rangeQuery(rp.getField()).from(rp.getStart()).to(rp.getEnd());
				bqb.must(qb);
			}
			
		}
		srb.setQuery(bqb);
		
		// id集合过滤
		String[] idsList = queryParams.getIds();
		if (null != idsList && idsList.length > 0) {
			QueryBuilder qb = QueryBuilders.idsQuery().addIds(idsList);
			bqb.must(qb);
		}
		
		//返回字段集
//		String[] returnFieldsArray = queryParams.getReturnFieldsArray();
//		if(returnFieldsArray != null && returnFieldsArray.length >0){
//			srb.addFields(returnFieldsArray);
//		}
		
		//返回值范围--深度分页问题，此处得细化，10000
		if (queryParams.getStart() >= 0 && queryParams.getSize() > 0 && (queryParams.getStart()< 10000)) {
			if(queryParams.getStart() + queryParams.getSize() <=10000){
				srb.setFrom(queryParams.getStart()).setSize(queryParams.getSize());
			}else{
				srb.setFrom(queryParams.getStart()).setSize(10000 - queryParams.getStart());
			}
			
		}
		//聚合检索
		AggsTermParams aggsParam = queryParams.getAggsParams();
		AggsHistogramParams histogram = queryParams.getHistogramParams();
		if(null != aggsParam){//term聚合检索
			String aggs_name = aggsParam.getAggName();
			String field = aggsParam.getField();
			Integer size = aggsParam.getSize();
			String sort = aggsParam.getSort();
			if(null !=size && size>0){
				if(ESConstantUtil.SORT_ASC.equals(sort)){
					TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).size(size).order(Terms.Order.count(true));
					srb.addAggregation(aggs);
				}else{
					TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).size(size).order(Terms.Order.count(false));
					srb.addAggregation(aggs);
				}
				
			}else{
				if(ESConstantUtil.SORT_ASC.equals(sort)){
					TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).order(Terms.Order.count(true));
					srb.addAggregation(aggs);
				}else{
					TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).order(Terms.Order.count(false));
					srb.addAggregation(aggs);
				}
				
			}
		}else if(null != histogram){
			//histogram 聚合检索
			String aggs_name2 = histogram.getAggsName();
			String field2 = histogram.getAggsField();
			String timeZone = histogram.getTimeZone();
			String format = histogram.getFormat();
			
			Date minDate = histogram.getMinDate();
			Date maxDate = histogram.getMaxDate();
			Long min = null, max = null ;
			if(null != minDate){
				min = minDate.getTime();
			}
			if(null != maxDate){
				max = maxDate.getTime();
			}
			DateHistogramInterval interval = histogram.getInterval();
			@SuppressWarnings("rawtypes")
			AggregationBuilder aggregation =
			        AggregationBuilders
			                .dateHistogram(aggs_name2)
			                .field(field2)
			                .timeZone(timeZone)
			                .format(format).extendedBounds(min, max)
			                .interval(interval);
			
			srb.addAggregation(aggregation);
		}
				
		//检索排序
		List<SortParams> sortList = queryParams.getSortList();
		if(null != sortList){
			for(SortParams sp : sortList){
				String sortField = sp.getSortField();
				String sort = sp.getSort();
				if(ESConstantUtil.SORT_ASC.equals(sort)){
					srb.addSort(sortField, SortOrder.ASC);
				}else{
					srb.addSort(sortField, SortOrder.DESC);
				}
			}
		}
			
		return srb.setExplain(false);
		
	}
	
	/**
	 * <br/>Description:bool 短语检索式构建
	 * <p>Author:jlchen/陈金梁</p>
	 * @param mp
	 * @return
	 */
	private BoolQueryBuilder transMatchParamsToPhraseQueryBuilder(MatchParams mp) {
		BoolQueryBuilder mpbqb = QueryBuilders.boolQuery();
		List<String> fieldList = mp.getFieldList();
		List<String> valueList = mp.getValueList();
		
		String in_opt = mp.getInOpt();//matchParams内部，多个检索字段间的操作方式，and、or
		String field_opt = mp.getFieldOpt();//field分词后，各个词汇间的连接方式及同一字段内，各个value操作方式，and、or
		//设置默认连接符
		Operator fieldOpt = Operator.AND;//field分词后词汇间默认采用and连接
		if(ESConstantUtil.OPT_OR.equals(field_opt)){//field分词后词汇间采用连接
			fieldOpt = Operator.OR;
		}
		
		for(int i = 0 ; fieldList!= null && i < fieldList.size(); i++){
			String field = fieldList.get(i);
			BoolQueryBuilder fieldqb = QueryBuilders.boolQuery();
			for(int j = 0; valueList != null && j < valueList.size(); j ++){
				String value = valueList.get(j);
				String analyzer = mp.getAnaylzer();
				QueryBuilder qb;
				if(ESConstantUtil.ANALYZER_IK_SMART.equals(analyzer)){
					if(mp.getType() == null){
						qb = QueryBuilders.matchQuery(field, value).operator(fieldOpt)
								.analyzer(analyzer);
					}else{
						qb = QueryBuilders.matchQuery(field, value).type(mp.getType())
								.analyzer(analyzer);
					}
				}else if(analyzer != null && analyzer.equals(ESConstantUtil.ANALYZER_NOT)){
						qb = QueryBuilders.matchQuery(field, value);
				}else{
					if(mp.getType() == null){
						qb = QueryBuilders.matchQuery(field, value).operator(fieldOpt)
								.analyzer(ESConstantUtil.ANALYZER_IK_MAX);
					}else{
						qb = QueryBuilders.matchQuery(field, value).type(mp.getType()).analyzer(analyzer);
					}
				}
				if(Operator.OR.equals(fieldOpt)){
					fieldqb.should(qb);
				}else{//默认and连接。同一字段内，各个value操作方式，and、or
					fieldqb.must(qb);
				}
			}
			//matchParams内部，多个检索字段间的操作方式，and、or
			if(ESConstantUtil.OPT_OR.equals(in_opt)){
				mpbqb.should(fieldqb);
			}else if(ESConstantUtil.OPT_NOT.equals(in_opt)){
				mpbqb.mustNot(fieldqb);
			}else if(ESConstantUtil.OPT_FILTER.equals(in_opt)){//过滤查询
				mpbqb.filter(fieldqb);
			}else{//默认or连接
				mpbqb.should(fieldqb);
			}
		}
		return mpbqb;
	}
}
