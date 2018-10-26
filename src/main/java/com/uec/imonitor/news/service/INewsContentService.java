package com.uec.imonitor.news.service;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.news.bean.NewsContentEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻内容service接口 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface INewsContentService {
	
	/**
	 * <br/>Description:通过webpageCode查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 */
	public NewsContentEntity findByWebpageCode(String webpageCode) throws BaseException, Exception;
	
	
}
