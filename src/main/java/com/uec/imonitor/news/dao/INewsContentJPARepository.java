package com.uec.imonitor.news.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.uec.imonitor.news.bean.NewsContentEntity;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻内容数据层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface INewsContentJPARepository extends JpaRepository<NewsContentEntity,String>{
	
}
