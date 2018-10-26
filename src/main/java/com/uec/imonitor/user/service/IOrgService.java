package com.uec.imonitor.user.service;

import java.util.List;


import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.user.bean.OrgEntity;
import com.uec.imonitor.user.bean.OrgUserEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 组织用户关系业务逻辑类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IOrgService {
	
	/**
	 * <br/>Description:添加组织
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param org
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public OrgEntity addOrg(OrgEntity org) throws BaseException, Exception;
	
	/**
	 * <br/>Description:修改组织
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param org
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public OrgEntity updateOrg(OrgEntity org) throws BaseException, Exception;
	
	/**
	 * <br/>Description:删除组织
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @throws BaseException
	 * @throws Exception
	 */
	public void deleteOrg(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据id查询组织
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public OrgEntity findOrgById(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:新增组织用户关系
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param orgUser
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public OrgUserEntity addOrgUser(OrgUserEntity orgUser) throws BaseException, Exception;
	
	/**
	 * <br/>Description:修改组织用户关系信息
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param user
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public OrgUserEntity updateOrgUser(OrgUserEntity orgUser) throws BaseException, Exception;
	
	/**
	 * <br/>Description:删除组织用户关系
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @throws BaseException
	 * @throws Exception
	 */
	public void deleteOrgUser(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据id查询组织用户关系
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public OrgUserEntity findOrgUserById(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据用户id查询组织用户关系
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param userId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<OrgUserEntity> findOrgUserByUserId(Integer userId) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据组织id查询用户组织关系
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param orgId
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<OrgUserEntity> findOrgUserByOrgId(Integer orgId) throws BaseException, Exception;
	
}
