package com.uec.imonitor.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.news.bean.NewsWebpageEntity;
import com.uec.imonitor.news.dao.INewsWebpageJPARepository;
import com.uec.imonitor.news.service.INewsWebpageService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻Service </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("newsWebpageService")
@Transactional(value="transactionManager")
public class NewsWebpageServiceImpl extends BaseService implements INewsWebpageService {

	@Autowired
	private INewsWebpageJPARepository newsWebpageRepository;

	/**
	 * <p>Description:根据webpageCode查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByWebpageCode
	 * @param webpageCode
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.news.service.INewsWebpageService#findByWebpageCode(java.lang.String)
	 */
	@Override
	public NewsWebpageEntity findByWebpageCode(String webpageCode)
			throws BaseException, Exception {
		NewsWebpageEntity entity = newsWebpageRepository.findOne(webpageCode);
		return entity;
	}

	/**
	 * <p>Description:根据新闻类型查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByNewsType
	 * @param newsType
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.news.service.INewsWebpageService#findByNewsType(java.lang.Integer)
	 */
	@Override
	public List<NewsWebpageEntity> findByNewsType(Integer newsType)
			throws BaseException, Exception {
		if(null == newsType){
			throw new RequestParamException(new String[]{"newsType"});
		}
		List<NewsWebpageEntity> list = newsWebpageRepository.findByNewsType(newsType);
		return list;
	}
	

}
