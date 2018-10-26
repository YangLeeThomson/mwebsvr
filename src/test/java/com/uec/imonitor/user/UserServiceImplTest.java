package com.uec.imonitor.user;

import static org.junit.Assert.*;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.user.bean.UserEntity;
import com.uec.imonitor.user.service.IUserService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Test
	public void testAddUser() throws BaseException, Exception {
		UserEntity user = new UserEntity();
		user.setUserName("szdst");
		String password = "szdst";
		user.setSalt(UUID.randomUUID().toString());
		user.setPassword(CommonUtil.encryptPassword(new Sha256Hash(password).toString(), user.getSalt()));
		userService.addUser(user);
	}


}
