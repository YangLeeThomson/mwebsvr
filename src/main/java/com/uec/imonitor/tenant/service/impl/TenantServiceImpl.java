package com.uec.imonitor.tenant.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.tenant.bean.TenantEntity;
import com.uec.imonitor.tenant.dao.ITenantJPARepository;
import com.uec.imonitor.tenant.service.ITenantService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 租户service </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("tenantService")
@Transactional(value="transactionManager")
public class TenantServiceImpl extends BaseService implements ITenantService {
	
	@Autowired
	private ITenantJPARepository tenantRepository;

	/**
	 * <p>Description:根据id查询租户</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findById
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.tenant.service.ITenantService#findById(int)
	 */
	@Override
	public TenantEntity findById(int id) throws BaseException, Exception {
		if(0 == id){
			throw new RequestParamException(new String[]{"id"});
		}
		TenantEntity entity = tenantRepository.findOne(id);
		return entity;
	}

	/**
	 * <p>Description:添加租户</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: add
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.tenant.service.ITenantService#add(com.uec.imonitor.tenant.bean.TenantEntity)
	 */
	@Override
	public TenantEntity add(TenantEntity tenant) throws BaseException, Exception {
		if(null == tenant){
			throw new RequestParamException(new String[]{"tenant"});
		}
		TenantEntity entity = tenantRepository.save(tenant);
		return entity;
	}

	/**
	 * <p>Description:修改租户</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: update
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.tenant.service.ITenantService#update(com.uec.imonitor.tenant.bean.TenantEntity)
	 */
	@Override
	public TenantEntity update(TenantEntity tenant) throws BaseException, Exception {
		if(null == tenant){
			throw new RequestParamException(new String[]{"tenant"});
		}
		TenantEntity entity = tenantRepository.save(tenant);
		return entity;
	}

	/**
	 * <p>Description:删除租户</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: delete
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.tenant.service.ITenantService#delete(java.lang.Integer)
	 */
	@Override
	public boolean delete(Integer id) throws BaseException, Exception {
		if(null == id){
			throw new RequestParamException(new String[]{"id"});
		}
		tenantRepository.delete(id);
		return true;
	}

	/**
	 * <p>Description:查询所有租户</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: listAll
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.tenant.service.ITenantService#listAll()
	 */
	@Override
	public List<TenantEntity> listAll() throws BaseException, Exception {
		List<TenantEntity> list = tenantRepository.findAll();
		return list;
	}

	/**
	 * <p>Description:g根据租户标记查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByTenantMark
	 * @param tenantMark
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.tenant.service.ITenantService#findByTenantMark(java.lang.String)
	 */
	@Override
	public TenantEntity findByTenantMark(String tenantMark)
			throws BaseException, Exception {
		if(StringUtils.isBlank(tenantMark)){
			throw new RequestParamException(new String[]{"tenantMark"});
		}
		TenantEntity entity = tenantRepository.findByTenantMark(tenantMark);
		return entity;
	}

}
