package com.uec.imonitor.request.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.request.bean.RequestSiteEntity;
import com.uec.imonitor.request.dao.IRequestSiteJPARepository;
import com.uec.imonitor.request.service.IRequestSiteService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 需求-网站关联service </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("requestSiteService")
@Transactional(value="transactionManager")
public class RequestSiteServiceImpl extends BaseService implements IRequestSiteService {
	
	@Autowired
	private IRequestSiteJPARepository requestSiteRepository;

	/**
	 * <p>Description:根据id查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findById
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.IRequestSiteService#findById(int)
	 */
	@Override
	public RequestSiteEntity findById(int id) throws BaseException, Exception {
		if(0 == id){
			throw new RequestParamException(new String[]{"id"});
		}
		RequestSiteEntity entity = requestSiteRepository.findOne(id);
		return entity;
	}

	/**
	 * <p>Description:添加需求监控的网站</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: add
	 * @param request
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.IRequestSiteService#add(com.uec.imonitor.request.bean.RequestSiteEntity)
	 */
	@Override
	public RequestSiteEntity add(RequestSiteEntity request)
			throws BaseException, Exception {
		if(null == request){
			throw new RequestParamException(new String[]{"request"});
		}
		RequestSiteEntity entity = requestSiteRepository.save(request);
		return entity;
	}

	/**
	 * <p>Description:修改需求监控的网站</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: update
	 * @param request
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.IRequestSiteService#update(com.uec.imonitor.request.bean.RequestSiteEntity)
	 */
	@Override
	public RequestSiteEntity update(RequestSiteEntity request)
			throws BaseException, Exception {
		if(null == request){
			throw new RequestParamException(new String[]{"request"});
		}
		RequestSiteEntity entity = requestSiteRepository.save(request);
		return entity;
	}

	/**
	 * <p>Description:删除需求监控的网站</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: delete
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.IRequestSiteService#delete(java.lang.Integer)
	 */
	@Override
	public boolean delete(Integer id) throws BaseException, Exception {
		if(null == id){
			throw new RequestParamException(new String[]{"id"});
		}
		requestSiteRepository.delete(id);
		return true;
	}

	/**
	 * <p>Description:根据需求id查询监控的网站</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByRequestId
	 * @param requestId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.request.service.IRequestSiteService#findByRequestId(java.lang.Integer)
	 */
	@Override
	public List<RequestSiteEntity> findByRequestId(Integer requestId)
			throws BaseException, Exception {
		if(null == requestId){
			throw new RequestParamException(new String[]{"requestId"});
		}
		List<RequestSiteEntity> list = requestSiteRepository.findByRequestId(requestId);
		return list;
	}




}
