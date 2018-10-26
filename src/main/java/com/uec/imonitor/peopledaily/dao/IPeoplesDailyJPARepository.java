package com.uec.imonitor.peopledaily.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uec.imonitor.peopledaily.bean.PeoplesDailyEntity;


//继承JpaRepository<EventBasic,Integer>，可以直接调用JPA中的很多自带方法
public interface IPeoplesDailyJPARepository extends JpaRepository<PeoplesDailyEntity,Integer>,JpaSpecificationExecutor<PeoplesDailyEntity>{

	@Query("from PeoplesDailyEntity a where a.webpageCode = :webpageCode")
	public PeoplesDailyEntity findByWebpageCode(@Param("webpageCode")String webpageCode);
	
	@Query("from PeoplesDailyEntity a where a.isCore = :isCore and a.status = :status ")
	public List<PeoplesDailyEntity> findByIsCoreAndStatus(@Param("isCore")Integer isCore,@Param("status")Integer status);

	/**
	* <p>Description:接受数据：当前时间一个小时内，ORG分组</p>
	* <p>Author:xkwang/王西坤</p>
	* @Title
	* @params  * @param null
	* @return
	*/
	@Query(value="select count(*) as count, org  as org from news_peopledaily where create_datetime > date_sub(NOW(),interval 60 minute) group by org ",nativeQuery=true)
	public List<Object> groupByOrgHour();

	@Query(value="select count(*) as count, org  as org from news_peopledaily where create_datetime > date_format(now(), '%Y-%m-%d 00:00:00') group by org ",nativeQuery=true)
	public List<Object> groupByOrgHourAll();

	/**
	 * <p>Description:接受数据：当前时间一个小时内，channel分组</p>
	 * <p>Author:xkwang/王西坤</p>
	 * @Title
	 * @params  * @param null
	 * @return
	 */
	@Query(value="select count(*) as count, channel  as channel from news_peopledaily where create_datetime > date_sub(NOW(),interval 60 minute) group by channel ",nativeQuery=true)
	public List<Object> groupByChannelHour();

	@Query(value="select count(*) as count, channel  as channel from news_peopledaily where create_datetime > date_format(now(), '%Y-%m-%d 00:00:00') group by channel ",nativeQuery=true)
	public List<Object> groupByChannelHourAll();



	/**
	 * <p>Description:推送的数据：当前时间一个小时内,channel</p>
	 * <p>Author:xkwang/王西坤</p>
	 * @Title
	 * @params  * @param null
	 * @return
	 */
	//接受的数据
	@Query(value="select count(*) as count ,channel as channel from news_peopledaily where status = 2 and is_core = 1 and create_datetime > date_sub(NOW(),interval 60 minute) group by channel ",nativeQuery=true)
	public List<Object> pushDataChannelHour();

	@Query(value="select count(*) as count ,channel as channel from news_peopledaily where status = 2 and is_core = 1 and create_datetime > date_format(now(), '%Y-%m-%d 00:00:00') group by channel ",nativeQuery=true)
	public List<Object> pushDataChannelHourAll();

	/**
	 * <p>Description:推送的数据：当前时间一个小时内,org</p>
	 * <p>Author:xkwang/王西坤</p>
	 * @Title
	 * @params  * @param null
	 * @return
	 */
	//接受的数据
	@Query(value="select count(*) as count ,org  as org from news_peopledaily where status = 2 and is_core = 1 and create_datetime > date_sub(NOW(),interval 60 minute) group by org ",nativeQuery=true)
	public List<Object> pushDataOrgHour();

	@Query(value="select count(*) as count ,org  as org from news_peopledaily where status = 2 and is_core = 1 and create_datetime > date_format(now(), '%Y-%m-%d 00:00:00') group by org ",nativeQuery=true)
	public List<Object> pushDataOrgHourAll();

}
