package com.uec.imonitor.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uec.imonitor.user.bean.OrgEntity;


//继承JpaRepository<EventBasic,Integer>，可以直接调用JPA中的很多自带方法
public interface IOrgJPARepository extends JpaRepository<OrgEntity,Integer>{

	
}
