package com.uec.imonitor.request.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.request.bean.RequestSiteEntity;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 需求-网站关系数据层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IRequestSiteJPARepository extends JpaRepository<RequestSiteEntity,Integer>{
	
	@Query("from RequestSiteEntity a where a.requestId = :requestId")
	public List<RequestSiteEntity> findByRequestId(@Param("requestId")Integer requestId);
	
	
}
