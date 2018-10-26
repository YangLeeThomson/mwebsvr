package com.uec.imonitor.user.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.user.bean.UserEntity;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 用户业务逻辑类 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public interface IUserService {
	
	/**
	 * <br/>Description:添加用户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param user
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public UserEntity addUser(UserEntity user) throws BaseException, Exception;
	
	/**
	 * <br/>Description:修改用户信息
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param user
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public UserEntity updateUser(UserEntity user) throws BaseException, Exception;
	
	/**
	 * <br/>Description:删除用户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @throws BaseException
	 * @throws Exception
	 */
	public void deleteUser(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据id查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public UserEntity findById(Integer id) throws BaseException, Exception;
	
	/**
	 * <br/>Description:根据用户名查询
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param userName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public UserEntity findByUserName(String userName) throws BaseException, Exception;
	
	/**
	 * <br/>Description:查询用户列表
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public List<UserEntity> listUsers() throws BaseException, Exception;
	
	/**
	 * <br/>Description:分页查询用户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param pageStart
	 * @param pageSize
	 * @param param
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
//	public Page<UserEntity> pageUsers(int pageStart,int pageSize,UserParam param) throws BaseException, Exception;

	/**
	 * <br/>Description:修改密码
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param innerid
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	public boolean updatePassword(Integer innerid, String oldPassword,String newPassword) throws BaseException, Exception;
}
