package com.uec.imonitor.config.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.config.bean.ConfigTenantParamEntity;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 租户参数关联数据层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IConfigTenantParamJPARepository extends JpaRepository<ConfigTenantParamEntity,Integer>{
	
	@Query("from ConfigTenantParamEntity a where a.tenantId = :tenantId ")
	public List<ConfigTenantParamEntity> findByTenantId(@Param("tenantId")Integer tenantId);
	
	@Query("from ConfigTenantParamEntity a where a.tenantId = :tenantId and a.paramId = :paramId ")
	public ConfigTenantParamEntity findByTenantIdAndParamId(@Param("tenantId")Integer tenantId,@Param("paramId")Integer paramId);
	
	@Query("from ConfigTenantParamEntity a where a.tenantId = :tenantId and a.paramName = :paramName ")
	public ConfigTenantParamEntity findByTenantIdAndParamName(@Param("tenantId")Integer tenantId,@Param("paramName")String paramName);
}
