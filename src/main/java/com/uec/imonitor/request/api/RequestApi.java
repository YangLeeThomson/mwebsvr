
package com.uec.imonitor.request.api;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uec.imonitor.common.base.BaseController;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.TenantRequestEntity;
import com.uec.imonitor.request.service.IRequestNewsService;
import com.uec.imonitor.request.service.ITenantRequestService;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: 北京荣之联科技股份有限公司 http://www.ronglian.com</p>
 * <p>Description: 需求接口 </p>
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Scope("prototype")
@RestController
@RequestMapping(value = "/{tenantMark}/api/request")
public class RequestApi extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("tenantRequestService")
	private ITenantRequestService tenantRequestService;
	
	@Autowired
	@Qualifier("requestNewsService")
	private IRequestNewsService requestNewsService;

	/**
	 * <br/>Description:获取租户的需求列表
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param tenantId
	 * @param model
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTenantRequest",method = RequestMethod.GET)
	public ModelMap getTenantRequest(Integer tenantId, Model model) throws BaseException,Exception {
		if(null == tenantId){
			tenantId = (Integer) session.getAttribute("tenantId");
		}
		List<TenantRequestEntity> list = tenantRequestService.findByTenantId(tenantId);
		return super.getModelMap(list);
	}
	
	/**
	 * <br/>Description:添加原创文章
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param entity
	 * @param model
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveRequestNews",method = RequestMethod.POST)
	public ModelMap saveRequestNews(RequestNewsEntity entity, Model model) throws BaseException,Exception {
		requestNewsService.saveRequestNews(entity);
		return super.getModelMap("添加原创文章成功");
	}

}
