package com.uec.imonitor.config.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.config.bean.ConfigTenantParamEntity;
import com.uec.imonitor.config.dao.IConfigTenantParamJPARepository;
import com.uec.imonitor.config.service.IConfigTenantParamService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 参数租户关联sevice </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("configTenantParamService")
@Transactional(value="transactionManager")
public class ConfigTenantParamServiceImpl extends BaseService implements IConfigTenantParamService {
	
	
	@Autowired
	private IConfigTenantParamJPARepository configTenantParamRepository;

	/**
	 * <p>Description:根据id查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findById
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.config.service.IConfigTenantParamService#findById(int)
	 */
	@Override
	public ConfigTenantParamEntity findById(Integer id) throws BaseException,
			Exception {
		if(null == id){
			throw new RequestParamException(new String[]{"id"});
		}
		ConfigTenantParamEntity entity = configTenantParamRepository.findOne(id);
		return entity;
	}

	/**
	 * <p>Description:添加租户配置</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: save
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.config.service.IConfigTenantParamService#save(com.uec.imonitor.config.bean.ConfigTenantParamEntity)
	 */
	@Override
	public ConfigTenantParamEntity save(ConfigTenantParamEntity entity)
			throws BaseException, Exception {
		if(null == entity){
			throw new RequestParamException(new String[]{"entity"});
		}
		ConfigTenantParamEntity save = configTenantParamRepository.save(entity);
		return save;
	}

	/**
	 * <p>Description:修改租户配置</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: update
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.config.service.IConfigTenantParamService#update(com.uec.imonitor.config.bean.ConfigTenantParamEntity)
	 */
	@Override
	public ConfigTenantParamEntity update(ConfigTenantParamEntity entity)
			throws BaseException, Exception {
		if(null == entity){
			throw new RequestParamException(new String[]{"entity"});
		}
		ConfigTenantParamEntity save = configTenantParamRepository.save(entity);
		return save;
	}

	/**
	 * <p>Description:删除租户配置</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: delete
	 * @param id
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.config.service.IConfigTenantParamService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws BaseException,
			Exception {
		if(null == id){
			throw new RequestParamException(new String[]{"id"});
		}
		configTenantParamRepository.delete(id);
	}

	/**
	 * <p>Description:根据租户id查询配置</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByTenantId
	 * @param tenantId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.config.service.IConfigTenantParamService#findByTenantId(java.lang.Integer)
	 */
	@Override
	public List<ConfigTenantParamEntity> findByTenantId(Integer tenantId)
			throws BaseException, Exception {
		if(null == tenantId){
			throw new RequestParamException(new String[]{"tenantId"});
		}
		List<ConfigTenantParamEntity> list = configTenantParamRepository.findByTenantId(tenantId);
		return list;
	}

	/**
	 * <p>Description:根据租户id和参数id查询配置</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByTenantIdAndParamId
	 * @param tenantId
	 * @param paramId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.config.service.IConfigTenantParamService#findByTenantIdAndParamId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ConfigTenantParamEntity findByTenantIdAndParamId(Integer tenantId,
			Integer paramId) throws BaseException, Exception {
		if(null == tenantId){
			throw new RequestParamException(new String[]{"tenantId"});
		}
		if(null == paramId){
			throw new RequestParamException(new String[]{"paramId"});
		}
		ConfigTenantParamEntity entity = configTenantParamRepository.findByTenantIdAndParamId(tenantId, paramId);
		return entity;
	}

	/**
	 * <p>Description:根据租户id和参数名查询配置</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByTenantIdAndParamName
	 * @param tenantId
	 * @param paramName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.config.service.IConfigTenantParamService#findByTenantIdAndParamName(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ConfigTenantParamEntity findByTenantIdAndParamName(Integer tenantId,
			String paramName) throws BaseException, Exception {
		if(null == tenantId){
			throw new RequestParamException(new String[]{"tenantId"});
		}
		if(StringUtils.isBlank(paramName)){
			throw new RequestParamException(new String[]{"paramName"});
		}
		ConfigTenantParamEntity entity = configTenantParamRepository.findByTenantIdAndParamName(tenantId, paramName);
		return entity;
	}


}
