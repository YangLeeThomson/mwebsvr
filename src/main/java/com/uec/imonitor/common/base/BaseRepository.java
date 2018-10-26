package com.uec.imonitor.common.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 数据层基础类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class BaseRepository {
	
	@PersistenceContext
	public EntityManager entityManager;
}
