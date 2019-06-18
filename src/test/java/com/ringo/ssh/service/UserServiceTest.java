package com.ringo.ssh.service;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.dao.BaseTestCaseJunit44;
import com.ringo.ssh.entity.User;

public class UserServiceTest extends BaseTestCaseJunit44 {
	
	@Resource
	private UserService userService;
	
	@Test
	@Rollback(false)
	public void testUpdateUser() {
		User u=new User();
		u.setUserId(1);
		u.setUserName("Ringo");
		u.setEmail("13922924658@163.com");
		u.setPassword("10122");
		u.setActivationCode("abcd123");
		userService.updateUser(u);
	}

}
