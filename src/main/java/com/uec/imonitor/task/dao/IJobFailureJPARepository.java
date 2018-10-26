package com.uec.imonitor.task.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.task.bean.JobFailureEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 任务状态数据层 </p> 
 * <p>Author:cjl/陈金梁</p>
 */
//@RepositoryRestResource(path="users")//配置这个参数可以把Repository当做Rest访问，方便测试用
//继承JpaRepository<EsStatusEntity,Integer>，可以直接调用JPA中的很多自带方法
public interface IJobFailureJPARepository  extends JpaRepository<JobFailureEntity,Integer>{
	@Query("from JobFailureEntity a where a.jobName = :jobName" )
	public List<JobFailureEntity> listByJobName(@Param("jobName") String jobName);
	
	@Query("from JobFailureEntity a where a.jobName = :jobName and a.tableName = :tableName" )
	public List<JobFailureEntity> listByJobAndTableName(@Param("jobName") String jobName, @Param("tableName") String tableName);
	
	@Query(value = "select * from job_failure  a where a.job_name = :jobName and a.table_name = :tableName   order by innerid desc limit :topN", nativeQuery = true )
	public List<JobFailureEntity> listTopNByJobAndTableName(@Param("jobName") String jobName, @Param("tableName") String tableName, @Param("topN") Integer topN);
	
	@Query("from JobFailureEntity a where a.innerid = :innerid")
	public JobFailureEntity findByInnerId(@Param("innerid") Integer innerid);
}
