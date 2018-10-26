package com.uec.imonitor.request.service;



import java.util.List;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.request.bean.RequestSiteEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 需求-网站关系service接口 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IRequestSiteService {
	
	/**
	 * <br/>Description:通过id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 */
	public RequestSiteEntity findById(int id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:添加需求监控的网站
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public RequestSiteEntity add(RequestSiteEntity entity) throws BaseException, Exception;
	
	/**
	 * <br/>Description:修改需求监控的网站
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public RequestSiteEntity update(RequestSiteEntity entity) throws BaseException, Exception;
	
	/**
	 * <br/>Description:删除需求监控的网站
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenant
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean delete(Integer id) throws BaseException, Exception;
	
	
	/**
	 * <br/>Description:根据需求id查询监控的网站
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenantName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<RequestSiteEntity> findByRequestId(Integer requestId) throws BaseException, Exception;
	
}
