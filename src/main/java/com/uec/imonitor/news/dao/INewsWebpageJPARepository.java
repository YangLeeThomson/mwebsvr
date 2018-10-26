package com.uec.imonitor.news.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.news.bean.NewsWebpageEntity;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻数据层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface INewsWebpageJPARepository extends JpaRepository<NewsWebpageEntity,String>{
	
	@Query("from NewsWebpageEntity a where a.newsType = :newsType and isDeleted = 0")
	public List<NewsWebpageEntity> findByNewsType(@Param("newsType")Integer newsType);
	
}
