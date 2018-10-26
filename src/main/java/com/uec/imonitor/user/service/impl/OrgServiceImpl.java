package com.uec.imonitor.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.user.bean.OrgEntity;
import com.uec.imonitor.user.bean.OrgUserEntity;
import com.uec.imonitor.user.dao.IOrgJPARepository;
import com.uec.imonitor.user.dao.IOrgUserJPARepository;
import com.uec.imonitor.user.service.IOrgService;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 用户业务层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("orgService")
@Transactional(value = "transactionManager")
public class OrgServiceImpl implements IOrgService {
	
	@Autowired
	private IOrgJPARepository orgRepository;
	
	@Autowired
	private IOrgUserJPARepository orgUserRepository;

	@Override
	public OrgEntity addOrg(OrgEntity org) throws BaseException, Exception {
		OrgEntity entity = orgRepository.save(org);
		return entity;
	}

	@Override
	public OrgEntity updateOrg(OrgEntity org) throws BaseException, Exception {
		OrgEntity entity = orgRepository.save(org);
		return entity;
	}

	@Override
	public void deleteOrg(Integer id) throws BaseException, Exception {
		orgRepository.delete(id);
		
	}

	@Override
	public OrgEntity findOrgById(Integer id) throws BaseException, Exception {
		OrgEntity entity = orgRepository.findOne(id);
		return entity;
	}

	@Override
	public OrgUserEntity addOrgUser(OrgUserEntity orgUser)
			throws BaseException, Exception {
		OrgUserEntity entity = orgUserRepository.save(orgUser);
		return entity;
	}

	@Override
	public OrgUserEntity updateOrgUser(OrgUserEntity orgUser)
			throws BaseException, Exception {
		OrgUserEntity entity = orgUserRepository.save(orgUser);
		return entity;
	}

	@Override
	public void deleteOrgUser(Integer id) throws BaseException, Exception {
		orgUserRepository.delete(id);
	}

	@Override
	public OrgUserEntity findOrgUserById(Integer id) throws BaseException,
			Exception {
		OrgUserEntity entity = orgUserRepository.findOne(id);
		return entity;
	}

	@Override
	public List<OrgUserEntity> findOrgUserByUserId(Integer userId)
			throws BaseException, Exception {
		List<OrgUserEntity> list = orgUserRepository.findByUserId(userId);
		return list;
	}

	@Override
	public List<OrgUserEntity> findOrgUserByOrgId(Integer orgId)
			throws BaseException, Exception {
		List<OrgUserEntity> list = orgUserRepository.findByOrgId(orgId);
		return list;
	}
	

}
