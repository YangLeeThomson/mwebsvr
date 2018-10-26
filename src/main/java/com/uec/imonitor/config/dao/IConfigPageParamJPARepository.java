package com.uec.imonitor.config.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.config.bean.ConfigPageParamEntity;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 页面配置参数数据层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IConfigPageParamJPARepository extends JpaRepository<ConfigPageParamEntity,Integer>{
	
	@Query("from ConfigPageParamEntity a where a.name = :name ")
	public ConfigPageParamEntity findByName(@Param("name")String name);
	
	
}
