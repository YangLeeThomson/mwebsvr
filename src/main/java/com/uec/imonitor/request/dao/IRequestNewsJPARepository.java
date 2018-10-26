package com.uec.imonitor.request.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.RequestNewsShow;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 被监控新闻数据层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IRequestNewsJPARepository extends JpaRepository<RequestNewsEntity,Integer>{
	
	@Query("from RequestNewsEntity a where a.requestId = :requestId")
	public List<RequestNewsEntity> findByRequestId(@Param("requestId")Integer requestId);
	
	@Query("from RequestNewsEntity a where a.webpageCode = :webpageCode")
	public RequestNewsEntity findByWebpageCode(@Param("webpageCode")String webpageCode);
	
//	@Query(value="select rn.newsId, count(rn) as reprintCount, rn.requestId, rn.webpageCode, rn.title, rn.newsAuthor, rn.reportDatetime from RequestNewsEntity rn ,NewsSpreadingAnalysisEntity nsa where rn.requestId in (:requestIds) and rn.reportDatetime >= :startTime and rn.reportDatetime <= :endTime and rn.isDeleted = 0 and rn.newsId = nsa.newsId GROUP BY nsa.newsId")
//	public Page<RequestNewsShow> pageRequestNews(@Param("requestIds")List<Integer> requestIds,@Param("startTime")Date startTime,@Param("endTime")Date endTime,Pageable pageable);
//	
	@Query(value="select rn.newsId, count(nsa) as reprintCount from RequestNewsEntity rn left join rn.reprintNewsList nsa  where rn.requestId in (:requestIds) and rn.reportDatetime >= :startTime and rn.reportDatetime <= :endTime and rn.isDeleted = 0  GROUP BY rn.newsId")
	public Page<Object[]> pageRequestNews(@Param("requestIds")List<Integer> requestIds,@Param("startTime")Date startTime,@Param("endTime")Date endTime,Pageable pageable);
	
	@Query("from RequestNewsEntity a where a.originalCode like %:queryStr% or a.title like %:queryStr%")
	public List<RequestNewsEntity> findByOrigCodeOrTitle(@Param("queryStr")String queryStr);
	
}
