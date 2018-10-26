package com.uec.imonitor.es.search;

import com.uec.imonitor.es.bean.params.QueryParams;
import com.uec.imonitor.es.bean.result.BaseQueryResult;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description:  聚类结果全文检索</p> 
 * <p>Author:jlchen/陈金梁</p>
 */
public interface IDataSearch {
	
	/**
	 * <br/>Description:全文检索
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @param queryParams
	 * @return
	 */
	public <T> BaseQueryResult<T>  textSearcher(QueryParams<T> queryParams);
	
	/**
	 * <br/>Description:短语检索
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @param queryParams
	 * @return
	 */
	public <T> BaseQueryResult<T>  phraseSearcher(QueryParams<T> queryParams);

	/**
	 * <br/>Description: 柱状聚合检索
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @param queryParams
	 * @return
	 */
	public <T> BaseQueryResult<T> textHistogramSearcher(QueryParams<T> queryParams);
	/**
	 * <br/>Description: 获取所有最新新闻索引信息
	 * <p>Author:jlchen/陈金梁</p>
	 * @param <T>
	 * @returne
	 */
	public <T> BaseQueryResult<T> matchAllSearch(QueryParams<T> queryParams);
}
