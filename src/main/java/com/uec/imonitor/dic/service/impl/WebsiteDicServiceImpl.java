package com.uec.imonitor.dic.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.dic.bean.WebsiteDicEntity;
import com.uec.imonitor.dic.dao.IWebsiteDicJPARepository;
import com.uec.imonitor.dic.service.IWebsiteDicService;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 网站字典service </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("websiteDicService")
@Transactional(value="transactionManager")
public class WebsiteDicServiceImpl extends BaseService implements IWebsiteDicService {
	
//	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IWebsiteDicJPARepository websiteDicRepository;

	/**
	 * <p>Description:根据id查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findById
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.dic.service.IWebsiteDicService#findById(int)
	 */
	@Override
	public WebsiteDicEntity findById(int id) throws BaseException, Exception {
		if(id == 0){
			throw new RequestParamException(new String[]{"id"});
		}
		WebsiteDicEntity website = websiteDicRepository.findOne(id);
		return website;
	}

	/**
	 * <p>Description:获取网站分类（一级分类）</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: listWebsiteClassifi
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.dic.service.IWebsiteDicService#listWebsiteClassifi()
	 */
	@Override
	public List<WebsiteDicEntity> listWebsiteClassifi() throws BaseException,
			Exception {
		List<WebsiteDicEntity> list = websiteDicRepository.findByParentId(0, 0);
		return list;
	}

	/**
	 * <p>Description:获取所有网站（二级分类）</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: listWebsite
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.dic.service.IWebsiteDicService#listWebsite()
	 */
	@Override
	public List<WebsiteDicEntity> listWebsite() throws BaseException, Exception {
		List<WebsiteDicEntity> list = websiteDicRepository.findAllWebsite(0);
		return list;
	}

	/**
	 * <p>Description:根据名称查询网站</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findWebsiteByName
	 * @param name
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.imonitor.dic.service.IWebsiteDicService#findWebsiteByName(java.lang.String)
	 */
	@Override
	public WebsiteDicEntity findWebsiteByName(String name)
			throws BaseException, Exception {
		if(StringUtils.isBlank(name)){
			throw new RequestParamException(new String[]{"name"});
		}
		WebsiteDicEntity entity = websiteDicRepository.findWebsiteByName(name, 0);
		return entity;
	}

}
