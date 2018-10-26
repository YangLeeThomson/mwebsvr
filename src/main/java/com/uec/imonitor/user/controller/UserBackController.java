package com.uec.imonitor.user.controller;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uec.imonitor.common.base.BaseController;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.user.bean.UserEntity;
import com.uec.imonitor.user.service.IUserService;


/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 用户后端controller </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
@Scope("prototype")
@Controller
@RequestMapping(value = "/{tenantMark}/user/back")
public class UserBackController extends BaseController {
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	
	
	/**
	 * <br/>Description:创建用户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param user
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public ModelMap createUser(UserEntity user)  throws BaseException, Exception{
		user.setSalt(UUID.randomUUID().toString());
		user.setPassword(CommonUtil.encryptPassword(new Sha256Hash(user.getUserName()).toString(), user.getSalt()));
		userService.addUser(user);
		return super.getModelMap("创建用户成功");
	}
	
	/**
	 * <br/>Description:获取用户信息
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @param model
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getUser/{id}",method=RequestMethod.GET)
	public ModelMap getUser(@PathVariable("id") int id,Model model) throws BaseException, Exception {
		UserEntity user = userService.findById(id);
		return super.getModelMap(user);

	}
	
	/**
	 * <br/>Description:修改用户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param user
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ModelMap updateUser(UserEntity user)  throws BaseException, Exception{
		userService.updateUser(user);
		return super.getModelMap("修改用户成功");
	}
	
	/**
	 * <br/>Description:修改密码
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param innerid
	 * @param newPassword
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public ModelMap updatePassword(Integer innerid,String newPassword)  throws BaseException, Exception{
		UserEntity user = userService.findById(innerid);
		user.setSalt(UUID.randomUUID().toString());
		user.setPassword(CommonUtil.encryptPassword(newPassword, user.getSalt()));
		userService.addUser(user);
//		userService.updatePassword(innerid,oldPassword,newPassword);
		return super.getModelMap("修改密码成功");
	}
	
	/**
	 * <br/>Description:删除用户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param id
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public ModelMap deleteUser(@PathVariable("id") int id) throws BaseException, Exception{
		userService.deleteUser(id);
		return super.getModelMap("删除用户成功");
	}
	
	/**
	 * <br/>Description:分页查询用户
	 * <p>Author:xpguo/郭晓鹏</p>
	 * @param aoData
	 * @param param
	 * @return
	 * @throws BaseException
	 * @throws Exception
	 */
//	@ResponseBody
//	@RequestMapping(value = "/pageUsers",method = RequestMethod.GET)
//	public ModelMap pageUsers(DataTablesRequestEntity aoData,UserParam param) throws BaseException,Exception{
//		DataTablesResponseEntity<UserEntity> dataTables = new DataTablesResponseEntity<UserEntity>();
//		Page<UserEntity> page = userService.pageUsers(aoData.getiDisplayStart(), aoData.getiDisplayLength(), param);
//		dataTables.setiTotalDisplayRecords(page.getTotalElements());
//		dataTables.setiTotalRecords(page.getTotalElements());
//		dataTables.setsEcho(aoData.getsEcho());
//		dataTables.setAaData(page.getContent());
//		return super.getModelMap(dataTables);
//	}
}
