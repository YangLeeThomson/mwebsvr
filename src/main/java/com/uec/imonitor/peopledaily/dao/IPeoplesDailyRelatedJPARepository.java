package com.uec.imonitor.peopledaily.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uec.imonitor.peopledaily.bean.PeoplesDailyRelatedEntity;


//继承JpaRepository<EventBasic,Integer>，可以直接调用JPA中的很多自带方法
public interface IPeoplesDailyRelatedJPARepository extends JpaRepository<PeoplesDailyRelatedEntity,Integer>{

	
}
