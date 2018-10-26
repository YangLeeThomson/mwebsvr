package com.uec.imonitor.dic.service;


import java.util.List;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.dic.bean.WebsiteDicEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 网站字典sevice接口 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IWebsiteDicService {
	
	/**
	 * <br/>Description:通过id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 */
	public WebsiteDicEntity findById(int id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:获取网站分类（一级分类）
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<WebsiteDicEntity> listWebsiteClassifi() throws BaseException, Exception;
	
	/**
	 * <br/>Description:获取所有网站（二级分类）
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<WebsiteDicEntity> listWebsite() throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据名称查询网站
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param name
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public WebsiteDicEntity findWebsiteByName(String name) throws BaseException, Exception;
	
}
