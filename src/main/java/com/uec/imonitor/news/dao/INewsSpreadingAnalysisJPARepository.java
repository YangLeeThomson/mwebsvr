package com.uec.imonitor.news.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.news.bean.NewsSpreadingAnalysisEntity;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻传播分析数据层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface INewsSpreadingAnalysisJPARepository extends JpaRepository<NewsSpreadingAnalysisEntity,Integer>{
	
	@Query("from NewsSpreadingAnalysisEntity a where a.webpageCode = :webpageCode")
	public List<NewsSpreadingAnalysisEntity> findByWebpageCode(@Param("webpageCode")String webpageCode);
	
	@Query("from NewsSpreadingAnalysisEntity a where a.newsId = :newsId and isDeleted = 0")
	public List<NewsSpreadingAnalysisEntity> findByNewsId(@Param("newsId")Integer newsId);
	
	@Query("from NewsSpreadingAnalysisEntity a where a.newsId = :newsId and a.status = :status and isDeleted = 0")
	public List<NewsSpreadingAnalysisEntity> findByNewsIdAndStatus(@Param("newsId")Integer newsId,@Param("status")Integer status);

	@Query("from NewsSpreadingAnalysisEntity a where a.newsId = :newsId and a.webpageCode = :webpageCode and isDeleted = 0")
	public NewsSpreadingAnalysisEntity findByNewsIdAndCode(@Param("newsId")Integer newsId,@Param("webpageCode")String webpageCode);
	
}
