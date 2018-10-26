package com.uec.imonitor.user.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.BusinessException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.user.bean.UserEntity;
import com.uec.imonitor.user.dao.IUserJPARepository;
import com.uec.imonitor.user.service.IUserService;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 用户业务层 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Service("userService")
@Transactional(value = "transactionManager")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserJPARepository userRepository;
	

	/**
	 * <p>Description:添加用户</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: addUser
	 * @param user
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.user.service.IUserService#addUser(com.uec.inews.user.bean.UserEntity)
	 */
	@Override
	public UserEntity addUser(UserEntity user) throws BaseException, Exception {
		if(null == user){
			throw new RequestParamException(new String[]{"user"});
		}
		UserEntity entity = userRepository.save(user);
		return entity;
	}

	/**
	 * <p>Description:更新用户信息</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: updateUser
	 * @param user
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.user.service.IUserService#updateUser(com.uec.inews.user.bean.UserEntity)
	 */
	@Override
	public UserEntity updateUser(UserEntity user) throws BaseException,
			Exception {
		if(null == user){
			throw new RequestParamException(new String[]{"user"});
		}
		UserEntity oldUser = this.findById(user.getInnerid());
		oldUser.setName(user.getName());
		oldUser.setEmail(user.getEmail());
		oldUser.setMobilePhone(user.getMobilePhone());
		UserEntity entity = userRepository.save(oldUser);
		return entity;
	}

	/**
	 * <p>Description:删除用户</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: deleteUser
	 * @param id
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.user.service.IUserService#deleteUser(int)
	 */
	@Override
	public void deleteUser(Integer id) throws BaseException, Exception {
		if(null == id){ throw new RequestParamException(new String[]{"id"}); }
		userRepository.delete(id);
	}

	/**
	 * <p>Description:根据id查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findById
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.user.service.IUserService#findById(int)
	 */
	@Override
	public UserEntity findById(Integer id) throws BaseException, Exception {
		if(null == id){ throw new RequestParamException(new String[]{"id"}); }
		UserEntity entity = userRepository.findOne(id);
		return entity;
	}

	/**
	 * <p>Description:根据用户名查询</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: findByUserName
	 * @param userName
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.user.service.IUserService#findByUserName(java.lang.String)
	 */
	@Override
	public UserEntity findByUserName(String userName) throws BaseException,
			Exception {
		if(StringUtils.isBlank(userName)){
			throw new RequestParamException(new String[]{"userName"});
		}
		UserEntity entity = userRepository.findByUserName(userName);
		return entity;
	}

	/**
	 * <p>Description:查询所有用户</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: listUsers
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.user.service.IUserService#listUsers()
	 */
	@Override
	public List<UserEntity> listUsers() throws BaseException, Exception {
		List<UserEntity> list = userRepository.findAll();
		return list;
	}

	/**
	 * <p>Description:分页查询用户</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: pageUsers
	 * @param pageStart
	 * @param pageSize
	 * @param param
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.user.service.IUserService#pageUsers(int, int, com.uec.inews.user.bean.UserParam)
	 */
//	@Override
//	public Page<UserEntity> pageUsers(int pageStart, int pageSize,
//			UserParam param) throws BaseException, Exception {
//		Sort sort = new Sort(Direction.DESC,"createTime");
//		Pageable pageable = new PageRequest(pageStart/pageSize,pageSize,sort);
//		Page<UserEntity> page = null;
//		if(StringUtils.isBlank(param.getQueryStr())){
//			page = userRepository.findAll(pageable);
//		}else {
//			page = userRepository.QueryByName(param.getQueryStr(),pageable);
//		}
//		return page;
//	}

	/**
	 * <p>Description:修改密码</p>
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @Title: updatePassword
	 * @param innerid
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 * @see com.uec.inews.user.service.IUserService#updatePassword(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updatePassword(Integer innerid, String oldPassword,
			String newPassword) throws BaseException, Exception {
		UserEntity user = this.findById(innerid);
		if(null != user){
			if(user.getPassword().equals(CommonUtil.encryptPassword(oldPassword, user.getSalt()))){
				user.setSalt(UUID.randomUUID().toString());
				user.setPassword(CommonUtil.encryptPassword(newPassword, user.getSalt()));
				userRepository.save(user);
			}else{
				throw new BusinessException("004001003");
			}
		}else{
			throw new BusinessException("004001002");
		}
		return true;
	}

}
