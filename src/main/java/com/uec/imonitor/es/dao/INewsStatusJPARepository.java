package com.uec.imonitor.es.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.es.bean.NewsStatusEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 记录状态数据层 </p> 
 * <p>Author:cjl/陈金梁</p>
 */
//@RepositoryRestResource(path="users")//配置这个参数可以把Repository当做Rest访问，方便测试用
//继承JpaRepository<EsStatusEntity,Integer>，可以直接调用JPA中的很多自带方法
public interface INewsStatusJPARepository  extends JpaRepository<NewsStatusEntity,Integer>{

	@Query("from NewsStatusEntity a where a.tableName = :tableName" )
	public List<NewsStatusEntity> listByTableName(@Param("tableName") String tableName);
	
//	@Query("from EsStatusEntity a where a.tableName = :tableName order by innerid desc limit :topN"  )
	@Query(value = "select * from news_status  a where a.table_name = :tableName  order by innerid desc limit :topN", nativeQuery = true )
	public List<NewsStatusEntity> listByTableNameTopN(@Param("tableName") String tableName,@Param("topN") int topN);
	
	@Query("from NewsStatusEntity a where a.tableName = :tableName and a.status = :status")
	public List<NewsStatusEntity> listByTableNameAndStatus(@Param("tableName") String tableName,@Param("status") Integer status);
	
	@Query("from NewsStatusEntity a where a.tableName = :tableName and a.status = :status and a.webpageCode = :webpageCode" )
	public List<NewsStatusEntity> listByTableNameAndStatusAndWebpageCode(@Param("tableName") String tableName,@Param("status") Integer status,@Param("webpageCode") String webpageCode);
	
	@Query(value = "select * from news_status  a where a.table_name = :tableName and a.status in :statusList order by innerid desc limit :topN", nativeQuery = true )
	public List<NewsStatusEntity> listByTableNameAndStatusTopN(@Param("tableName") String tableName,@Param("statusList") List<Integer> statusList,@Param("topN") int topN);
}
