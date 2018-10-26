package com.uec.imonitor.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.config.bean.ConfigPageParamEntity;
import com.uec.imonitor.config.dao.IConfigPageParamJPARepository;
import com.uec.imonitor.config.service.IConfigPageParamService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 页面参数配置sevice </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("configPageParamService")
@Transactional(value="transactionManager")
public class ConfigPageParamServiceImpl extends BaseService implements IConfigPageParamService {
	
	
	@Autowired
	private IConfigPageParamJPARepository configPageParamRepository;

	/**
	 * <p>Description:根据id查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findById
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.config.service.IConfigPageParamService#findById(int)
	 */
	@Override
	public ConfigPageParamEntity findById(Integer id) throws BaseException,
			Exception {
		if(null == id){
			throw new RequestParamException(new String[]{"id"});
		}
		ConfigPageParamEntity entity = configPageParamRepository.findOne(id);
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
	 * @see com.uec.imonitor.config.service.IConfigPageParamService#save(com.uec.imonitor.config.bean.ConfigPageParamEntity)
	 */
	@Override
	public ConfigPageParamEntity save(ConfigPageParamEntity entity)
			throws BaseException, Exception {
		if(null == entity){
			throw new RequestParamException(new String[]{"entity"});
		}
		ConfigPageParamEntity save = configPageParamRepository.save(entity);
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
	 * @see com.uec.imonitor.config.service.IConfigPageParamService#update(com.uec.imonitor.config.bean.ConfigPageParamEntity)
	 */
	@Override
	public ConfigPageParamEntity update(ConfigPageParamEntity entity)
			throws BaseException, Exception {
		if(null == entity){
			throw new RequestParamException(new String[]{"entity"});
		}
		ConfigPageParamEntity save = configPageParamRepository.save(entity);
		return save;
	}

	/**
	 * <p>Description:删除租户配置</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: delete
	 * @param id
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.config.service.IConfigPageParamService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws BaseException,
			Exception {
		if(null == id){
			throw new RequestParamException(new String[]{"id"});
		}
		configPageParamRepository.delete(id);
	}




	@Override
	public List<ConfigPageParamEntity> listAll() throws BaseException,
			Exception {
		List<ConfigPageParamEntity> list = configPageParamRepository.findAll();
		return list;
	}


}
