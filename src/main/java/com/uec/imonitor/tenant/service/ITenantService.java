package com.uec.imonitor.tenant.service;



import java.util.List;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.tenant.bean.TenantEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 租户service接口 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface ITenantService {
	
	/**
	 * <br/>Description:通过id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 */
	public TenantEntity findById(int id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据租户标记查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public TenantEntity findByTenantMark(String tenantMark) throws BaseException, Exception;
	
	/**
	 * <br/>Description:添加租户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public TenantEntity add(TenantEntity tenant) throws BaseException, Exception;
	
	/**
	 * <br/>Description:修改租户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public TenantEntity update(TenantEntity tenant) throws BaseException, Exception;
	
	/**
	 * <br/>Description:删除租户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean delete(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:查询所有租户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<TenantEntity> listAll() throws BaseException, Exception;
}
