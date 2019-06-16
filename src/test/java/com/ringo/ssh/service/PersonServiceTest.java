package com.ringo.ssh.service;

import javax.annotation.Resource;
import org.junit.Test;
import com.ringo.ssh.dao.BaseTestCaseJunit44;
import com.ringo.ssh.exception.MyException;
public class PersonServiceTest extends BaseTestCaseJunit44{
	
	@Resource
	PersonService personService;
	@Test
	public void testcheckSignIn() {
		try {
			if(personService.checkSignIn("Winbee", "12")==1)
				System.out.println("账户存在且密码正确");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
