package com.uec.imonitor.request.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.request.bean.TenantRequestEntity;
import com.uec.imonitor.request.dao.ITenantRequestJPARepository;
import com.uec.imonitor.request.service.ITenantRequestService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 租户需求service </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("tenantRequestService")
@Transactional(value="transactionManager")
public class TenantRequestServiceImpl extends BaseService implements ITenantRequestService {
	
	@Autowired
	private ITenantRequestJPARepository tenantRequestRepository;

	/**
	 * <p>Description:根据id查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findById
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.ITenantRequestService#findById(int)
	 */
	@Override
	public TenantRequestEntity findById(int id) throws BaseException, Exception {
		if(0 == id){
			throw new RequestParamException(new String[]{"id"});
		}
		TenantRequestEntity entity = tenantRequestRepository.findOne(id);
		return entity;
	}

	/**
	 * <p>Description:新增租户需求</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: add
	 * @param request
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.ITenantRequestService#add(com.uec.imonitor.request.bean.TenantRequestEntity)
	 */
	@Override
	public TenantRequestEntity add(TenantRequestEntity request)
			throws BaseException, Exception {
		if(null == request){
			throw new RequestParamException(new String[]{"request"});
		}
		TenantRequestEntity entity = tenantRequestRepository.save(request);
		return entity;
	}

	/**
	 * <p>Description:修改租户需求</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: update
	 * @param request
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.ITenantRequestService#update(com.uec.imonitor.request.bean.TenantRequestEntity)
	 */
	@Override
	public TenantRequestEntity update(TenantRequestEntity request)
			throws BaseException, Exception {
		if(null == request){
			throw new RequestParamException(new String[]{"request"});
		}
		TenantRequestEntity entity = tenantRequestRepository.save(request);
		return entity;
	}

	/**
	 * <p>Description:删除租户需求</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: delete
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.ITenantRequestService#delete(java.lang.Integer)
	 */
	@Override
	public boolean delete(Integer id) throws BaseException, Exception {
		if(null == id){
			throw new RequestParamException(new String[]{"id"});
		}
		tenantRequestRepository.delete(id);
		return true;
	}

	/**
	 * <p>Description:查询所有租户需求</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: listAll
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.ITenantRequestService#listAll()
	 */
	@Override
	public List<TenantRequestEntity> listAll() throws BaseException, Exception {
		List<TenantRequestEntity> list = tenantRequestRepository.findAll();
		return list;
	}

	/**
	 * <p>Description:根据租户id查询需求</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByTenantId
	 * @param tenantId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.ITenantRequestService#findByTenantId(java.lang.Integer)
	 */
	@Override
	public List<TenantRequestEntity> findByTenantId(Integer tenantId)
			throws BaseException, Exception {
		if(null == tenantId){
			throw new RequestParamException(new String[]{"tenantId"});
		}
		List<TenantRequestEntity> list = tenantRequestRepository.findByTenantIdAndStatus(tenantId, 0);
		return list;
	}

	/**
	 * <p>Description:根据租户名称查询需求</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByTenantName
	 * @param tenantName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.ITenantRequestService#findByTenantName(java.lang.String)
	 */
	@Override
	public List<TenantRequestEntity> findByRequestName(String requestName)
			throws BaseException, Exception {
		if(StringUtils.isBlank(requestName)){
			throw new RequestParamException(new String[]{"requestName"});
		}
		List<TenantRequestEntity> list = tenantRequestRepository.findByRequestName(requestName);
		return list;
	}


}
