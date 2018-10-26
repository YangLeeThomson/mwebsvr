package com.uec.imonitor.request.service;



import java.util.List;

import com.uec.imonitor.common.base.PageResponse;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.news.bean.QueryNewsParam;
import com.uec.imonitor.request.bean.RequestNewsDetail;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.RequestNewsShow;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 被监控新闻service接口 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IRequestNewsService {
	
	/**
	 * <br/>Description:通过id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 */
	public RequestNewsEntity findById(int id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据webpageCode查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param webpageCode
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public RequestNewsEntity findByWebpageCode(String webpageCode) throws BaseException, Exception;
	
	/**
	 * <br/>Description:添加原创新闻
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public RequestNewsEntity add(RequestNewsEntity entity) throws BaseException, Exception;
	
	/**
	 * <br/>Description:添加原创新闻
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public RequestNewsEntity saveRequestNews(RequestNewsEntity entity) throws BaseException, Exception;
	
	/**
	 * <br/>Description:修改原创新闻
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public RequestNewsEntity update(RequestNewsEntity entity) throws BaseException, Exception;
	
	/**
	 * <br/>Description:删除原创新闻
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean delete(Integer id) throws BaseException, Exception;
	
	
	/**
	 * <br/>Description:根据需求id查询监控的新闻
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenantName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<RequestNewsEntity> findByRequestId(Integer requestId) throws BaseException, Exception;
	
	/**
	 * <br/>Description:通过需求新闻对象获取详情
	 * <p>Author:jlchen/陈金梁</p>
	 * @param entity
	 * @return
	 * @throws RequestParamException 
	 */
	public RequestNewsDetail findDetailByRequestNewsEntity(RequestNewsEntity entity) throws RequestParamException;
	
	/**
	 * <br/>Description:通过需求新闻id获取对象集
	 * <p>Author:jlchen/陈金梁</p>
	 * @param idList
	 * @return
	 * @throws RequestParamException 
	 */
	public List<RequestNewsDetail> listDetailByIds(List<Integer> idList) throws RequestParamException;

	/**
	 * <br/>Description:分页查询原始新闻(es查询)
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param param
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public BaseQueryResult<RequestNewsShow> pageRequestNewsEs(QueryNewsParam param) throws BaseException, Exception;
	
	/**
	 * <br/>Description:分页查询原始新闻(mysql查询)
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param param
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public PageResponse<RequestNewsShow> pageRequestNews(QueryNewsParam param) throws BaseException, Exception;
	
	/**
	 * <br/>Description:重做新增或更新失败任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @return
	 */
	public void insertAndUpdateIndexFailureJob();
	
	/**
	 * <br/>Description: 需求新闻新增索引任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @return
	 */
	public void insertRequestNewsIndexJob(int do_num);
	
	
	/**
	 * <br/>Description:需求新闻真删除任务
	 * <p>Author:jlchen/陈金梁</p>
	 */
	public void deleteRequestNewsJob(int do_num);
	/**
	 * <br/>Description:需求新闻更新索引任务
	 * <p>Author:jlchen/陈金梁</p>
	 * @param do_num
	 */
	public void updateRequestNewsIndexJob(int do_num);
}
