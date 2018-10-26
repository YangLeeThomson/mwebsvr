package com.uec.imonitor.news.service;

import java.util.List;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.news.bean.NewsWebpageEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻service接口 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface INewsWebpageService {
	
	/**
	 * <br/>Description:通过webpageCode查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 */
	public NewsWebpageEntity findByWebpageCode(String webpageCode) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据新闻类型查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param newsType
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<NewsWebpageEntity> findByNewsType(Integer newsType) throws BaseException, Exception;
}
