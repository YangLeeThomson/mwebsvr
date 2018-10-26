package com.uec.imonitor.news.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.news.bean.NewsContentEntity;
import com.uec.imonitor.news.dao.INewsContentJPARepository;
import com.uec.imonitor.news.service.INewsContentService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻内容service </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("newsContentService")
@Transactional(value="transactionManager")
public class NewsContentServiceImpl extends BaseService implements INewsContentService {

	@Autowired
	private INewsContentJPARepository newsContentRepository;
	
	/**
	 * <p>Description:根据webpageCode查询新闻内容</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByWebpageCode
	 * @param webpageCode
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.news.service.INewsContentService#findByWebpageCode(java.lang.String)
	 */
	@Override
	public NewsContentEntity findByWebpageCode(String webpageCode)
			throws BaseException, Exception {
		if(StringUtils.isBlank(webpageCode)){
			throw new RequestParamException(new String[]{"webpageCode"});
		}
		NewsContentEntity entity = newsContentRepository.findOne(webpageCode);
		return entity;
	}

}
