package com.uec.imonitor.config.service;


import java.util.List;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.config.bean.ConfigPageParamEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 页面参数配置sevice接口 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IConfigPageParamService {
	
	/**
	 * <br/>Description:通过id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 */
	public ConfigPageParamEntity findById(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:查询全部配置
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<ConfigPageParamEntity> listAll() throws BaseException, Exception;
	
	/**
	 * <br/>Description:添加
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public ConfigPageParamEntity save(ConfigPageParamEntity entity) throws BaseException, Exception;
	
	/**
	 * <br/>Description:修改
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param entity
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public ConfigPageParamEntity update(ConfigPageParamEntity entity) throws BaseException, Exception;
	
	/**
	 * <br/>Description:删除
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param entity
	 * @throws BaseException
	 * @throws Exception
	 */
	public void delete(Integer id) throws BaseException, Exception;
	
	
	
}
