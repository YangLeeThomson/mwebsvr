package com.uec.imonitor.news.service;

import java.util.List;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisDetail;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisEntity;
import com.uec.imonitor.news.bean.QueryNewsParam;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻传播分析service接口 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface INewsSpreadingAnalysisService {
	
	/**
	 * <br/>Description:通过webpageCode查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 */
	public List<NewsSpreadingAnalysisEntity> findByWebpageCode(String webpageCode) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据原创新闻查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param newsId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<NewsSpreadingAnalysisEntity> findByNewsId(Integer newsId) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据原始新闻id和转载新闻webpageCode查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param newsId
	 * @param webpageCode
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public NewsSpreadingAnalysisEntity findByNewsIdAndCode(Integer newsId,String webpageCode) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据原创新闻查询未标记转载的新闻
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param newsId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<NewsSpreadingAnalysisEntity> findByNewsIdAndNotMarked(Integer newsId) throws BaseException, Exception;
	
	/**
	 * <br/>Description:通过分析结果实体获取详情对象
	 * <p>Author:jlchen/陈金梁</p>
	 * @param entity
	 * @return
	 * @throws RequestParamException 
	 * @throws Exception 
	 * @throws BaseException 
	 */
	public NewsSpreadingAnalysisDetail findDetailByNewsSpreadingAnalysisEntity(NewsSpreadingAnalysisEntity entity) throws RequestParamException, BaseException, Exception;
	
	/**
	 * <br/>Description:通过id集合获取分析结果详情实体对象集
	 * <p>Author:jlchen/陈金梁</p>
	 * @param idList
	 * @return
	 * @throws RequestParamException 
	 * @throws Exception 
	 * @throws BaseException 
	 */
	public List<NewsSpreadingAnalysisDetail> listNewsSpreadingAnalysisDetailByIds(List<Integer> idList) throws RequestParamException, BaseException, Exception;

	/**
	 * <br/>Description:分页查询转载新闻
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param param
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public BaseQueryResult<NewsSpreadingAnalysisDetail> pageReprintNews(QueryNewsParam param)throws BaseException, Exception;
	
	/**
	 * <br/>Description:按日期统计
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param param
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public BaseQueryResult<NewsSpreadingAnalysisDetail> countByDate(QueryNewsParam param)throws BaseException, Exception;
	
	/**
	 * <br/>Description:重做新增或更新失败任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @return
	 */
	public void insertAndUpdateIndexFailureJob() ;
	
	/**
	 * <br/>Description: 新闻传播分析结果新增索引任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @return
	 */
	public void insertSpreadingAnalysisIndexJob(int do_num);
	
	
	/**
	 * <br/>Description:新闻传播分析真删除任务
	 * <p>Author:jlchen/陈金梁</p>
	 */
	public void deletedSpreadingAnalysisIndexJob(int do_num);
	/**
	 * <br/>Description:新闻传播分析更新索引任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @param do_num
	 */
	public void updateSpreadingAnalysisIndexJob(int do_num);
}
