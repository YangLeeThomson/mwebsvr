package com.uec.imonitor.dic.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.dic.bean.WebsiteDicEntity;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 网站字典数据层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IWebsiteDicJPARepository extends JpaRepository<WebsiteDicEntity,Integer>{
	
	@Query("from WebsiteDicEntity a where a.parentId = :parentId and a.status = :status ")
	public List<WebsiteDicEntity> findByParentId(@Param("parentId")Integer parentId,@Param("status")Integer status);
	
	@Query("from WebsiteDicEntity a where a.parentId != 0 and a.status = :status ")
	public List<WebsiteDicEntity> findAllWebsite(@Param("status")Integer status);
	
	@Query("from WebsiteDicEntity a where a.parentId != 0 and a.name = :name and a.status = :status ")
	public WebsiteDicEntity findWebsiteByName(@Param("name")String name,@Param("status")Integer status);
	
}
