package com.uec.imonitor.es.service;

import com.uec.imonitor.common.exception.BaseException;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 索引服务 </p> 
 * <p>Author:cjl/陈金梁</p>
 */
public interface IEsIndexService {
	
	/**
	 * <br/>Description:新闻传播结果索引初始化服务
	 * <p>Author:jlchen/陈金梁</p>
	 */
	public void indexNewsSpreadingSetting();
	
	/**
	 * <br/>Description:监控需求新闻索引初始化服务
	 * <p>Author:jlchen/陈金梁</p>
	 */
	public void indexRequsetNewsSetting();
	
	
	/**
	 * <br/>Description:索引初始化
	 * <p>Author:jlchen/陈金梁</p>
	 */
	public void EsIndexSetting();
	
	/**
	 * <br/>Description:索引所有需求新闻
	 * <p>Author:jlchen/陈金梁</p>
	 * @throws Exception 
	 * @throws BaseException 
	 */
	public boolean initAllRequestNews() throws BaseException, Exception;
	
	/**
	 * <br/>Description:索引所有分析结果新闻
	 * <p>Author:jlchen/陈金梁</p>
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean initAllNewsSpreading() throws BaseException, Exception;
	
	/**
	 * <br/>Description:给索引起别名
	 * <p>Author:jlchen/陈金梁</p>
	 * @param originalName
	 * @param aliaseName
	 */
	public void setIndexAliase(String originalName, String aliaseName);
}
