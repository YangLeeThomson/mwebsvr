package com.uec.imonitor.config.service;


import java.util.List;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.config.bean.ConfigTenantParamEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 参数租户关联sevice接口 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IConfigTenantParamService {
	
	/**
	 * <br/>Description:通过id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 */
	public ConfigTenantParamEntity findById(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:添加
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public ConfigTenantParamEntity save(ConfigTenantParamEntity entity) throws BaseException, Exception;
	
	/**
	 * <br/>Description:修改
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public ConfigTenantParamEntity update(ConfigTenantParamEntity entity) throws BaseException, Exception;
	
	/**
	 * <br/>Description:删除
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param entity
	 * @throws BaseException
	 * @throws Exception
	 */
	public void delete(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据租户id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenantId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<ConfigTenantParamEntity> findByTenantId(Integer tenantId) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据租户id和参数id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenantId
	 * @param paramId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public ConfigTenantParamEntity findByTenantIdAndParamId(Integer tenantId, Integer paramId) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据租户id和参数名称查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenantId
	 * @param paramName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public ConfigTenantParamEntity findByTenantIdAndParamName(Integer tenantId,String paramName) throws BaseException, Exception;
	
	
}
