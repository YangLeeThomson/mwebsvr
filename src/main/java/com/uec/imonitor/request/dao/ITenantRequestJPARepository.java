package com.uec.imonitor.request.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.request.bean.TenantRequestEntity;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 租户需求数据层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface ITenantRequestJPARepository extends JpaRepository<TenantRequestEntity,Integer>{
	
	@Query("from TenantRequestEntity a where a.tenantId = :tenantId and isDeleted = 0")
	public List<TenantRequestEntity> findByTenantId(@Param("tenantId")Integer tenantId);
	
	@Query("from TenantRequestEntity a where a.tenantId = :tenantId and a.status = :status and isDeleted = 0")
	public List<TenantRequestEntity> findByTenantIdAndStatus(@Param("tenantId")Integer tenantId,@Param("status")Integer status);
	
	
	@Query("from TenantRequestEntity a where a.requestName = :requestName and isDeleted = 0")
	public List<TenantRequestEntity> findByRequestName(@Param("requestName")String requestName);
	
	@Query("from TenantRequestEntity a where a.requestName = :requestName and a.status = :status and isDeleted = 0")
	public List<TenantRequestEntity> findByRequestNameAndStatus(@Param("requestName")String requestName,@Param("status")String status);
	
}
