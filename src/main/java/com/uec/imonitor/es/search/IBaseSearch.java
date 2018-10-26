package com.uec.imonitor.es.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;

import com.uec.imonitor.es.bean.params.QueryParams;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  ES基础检索方法封装</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public interface IBaseSearch {
	
	/**
	 * <br/>Description: 全文检索
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @param queryParams
	 * @return
	 */
	public <T> SearchResponse textSearcher(QueryParams<T> queryParams);
	
	/**
	 * <br/>Description:短语检索
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @param queryParams
	 * @return
	 */
	public <T> SearchResponse phraseSearcher(QueryParams<T> queryParams);
		
	/**
	 * <br/>Description: 检索式解析
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @param queryParams
	 * @param client
	 * @return
	 */
	public  <T> SearchRequestBuilder getSearchRequestBuilder(QueryParams<T> queryParams);
	
	/**
	 * <br/>Description: 查询所有结果
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @return
	 */
	public <T> SearchResponse matchAllSearch(QueryParams<T> queryParams);
}
