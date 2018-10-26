package com.uec.imonitor.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.user.bean.OrgUserEntity;



//继承JpaRepository<EventBasic,Integer>，可以直接调用JPA中的很多自带方法
public interface IOrgUserJPARepository extends JpaRepository<OrgUserEntity,Integer>{

	@Query("from OrgUserEntity a where a.userId = :userId")
	public List<OrgUserEntity> findByUserId(@Param("userId")Integer userId);
	
	
	@Query("from OrgUserEntity a where a.orgId = :orgId")
	public List<OrgUserEntity> findByOrgId(@Param("orgId")Integer orgId);
	
}
