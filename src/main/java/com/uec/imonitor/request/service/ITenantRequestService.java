package com.uec.imonitor.request.service;



import java.util.List;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.request.bean.TenantRequestEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 租户需求service接口 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface ITenantRequestService {
	
	/**
	 * <br/>Description:通过id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 */
	public TenantRequestEntity findById(int id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:添加租户需求
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public TenantRequestEntity add(TenantRequestEntity request) throws BaseException, Exception;
	
	/**
	 * <br/>Description:修改租户需求
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public TenantRequestEntity update(TenantRequestEntity request) throws BaseException, Exception;
	
	/**
	 * <br/>Description:删除租户需求
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean delete(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:查询所有需求
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<TenantRequestEntity> listAll() throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据租户id查询需求
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenantName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<TenantRequestEntity> findByTenantId(Integer tenantId) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据租户名称查询需求
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenantName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<TenantRequestEntity> findByRequestName(String requestName) throws BaseException, Exception;
}
