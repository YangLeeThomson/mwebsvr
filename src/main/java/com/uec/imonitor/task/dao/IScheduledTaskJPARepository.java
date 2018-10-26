package com.uec.imonitor.task.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uec.imonitor.task.bean.ScheduledTaskEntity;



/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司 http://www.ronglian.com</p> 
 * <p>Description: 定时任务 数据库操作 </p> 
 *<p>Author:jinlChen/陈金梁</p>
 */
@Repository("scheduledTaskJPARepository")
public interface IScheduledTaskJPARepository extends JpaRepository<ScheduledTaskEntity,Long>,Serializable{
	@Query("from ScheduledTaskEntity a where a.enable = :enable" )
	public List<ScheduledTaskEntity> listAllByEnable(@Param("enable") Boolean enable);
	
	@Query("from ScheduledTaskEntity a where a.identification = :identification" )
	public ScheduledTaskEntity findByIdentification(@Param("identification") String identification);
	
}
