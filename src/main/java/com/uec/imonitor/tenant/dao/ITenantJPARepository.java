package com.uec.imonitor.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.tenant.bean.TenantEntity;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 租户数据层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface ITenantJPARepository extends JpaRepository<TenantEntity,Integer>{
	
	
	@Query("from TenantEntity a where a.tenantName = :tenantName")
	public TenantEntity findByName(@Param("tenantName")String tenantName);
	
	@Query("from TenantEntity a where a.tenantMark = :tenantMark")
	public TenantEntity findByTenantMark(@Param("tenantMark")String tenantMark);
	
}
