package com.ringo.ssh.service;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.dao.BaseTestCaseJunit44;
import com.ringo.ssh.entity.User;
import com.ringo.ssh.exception.MyException;

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

	@Test
	public void testCheckSignIn() {
		try {
			if(userService.checkSignIn("13922924658@163.com", "10419")==1)
				System.out.println("存在此用户");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
