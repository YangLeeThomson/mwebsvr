package com.uec.imonitor.news.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.base.PageResponse;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.news.bean.NewsContentEntity;
import com.uec.imonitor.news.bean.QueryNewsParam;
import com.uec.imonitor.news.dao.INewsContentJPARepository;
import com.uec.imonitor.news.service.INewsContentService;
import com.uec.imonitor.news.service.IRequestNewsPoiService;
import com.uec.imonitor.request.bean.RequestNewsDetail;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.RequestNewsShow;
import com.uec.imonitor.request.dao.IRequestNewsJPARepository;
import com.uec.imonitor.request.service.IRequestNewsService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻内容service </p> 
 * <p>Author:xkwang/王西坤</p>
 */
@Service("requestNewsPoiService")
@Transactional(value="transactionManager")
public class RequestNewsPoiServiceImpl extends BaseService implements IRequestNewsPoiService {

	@Autowired
	private IRequestNewsJPARepository requestNewsRepository;
	
	/**
	 * 根据news_id查找转发数量
	 * @param requestIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	/*public  countRequestNews(Integer newsId){
		
		  requestNewsRepository.countRequestNews(newsId);
		
	}*/

}
