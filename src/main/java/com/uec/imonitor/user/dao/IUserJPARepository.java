package com.uec.imonitor.user.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.user.bean.UserEntity;



//继承JpaRepository<EventBasic,Integer>，可以直接调用JPA中的很多自带方法
public interface IUserJPARepository extends JpaRepository<UserEntity,Integer>{

	@Query("from UserEntity a where a.userName = :userName")
	public UserEntity findByUserName(@Param("userName")String userName);
	
	@Query("from UserEntity a where a.userName like %:queryStr% or a.name like %:queryStr%")
	public Page<UserEntity> QueryByName(@Param("queryStr")String queryStr,Pageable pageable);
	
	@Query("from UserEntity a where a.userName like %:queryStr% or a.name like %:queryStr%")
	public List<UserEntity> findByName(@Param("queryStr")String queryStr);
	
}
