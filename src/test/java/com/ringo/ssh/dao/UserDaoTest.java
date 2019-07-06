package com.ringo.ssh.dao;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.User;

public class UserDaoTest extends BaseTestCaseJunit44{

	//@Autowired
	@Resource(name="UserDao")
	private IUserDao userDao;	
	
	@Test
	@Rollback(false)
	public void testSave() {
		User u=new User();
		u.setUserName("Ringo");
		u.setEmail("13922924658@163.com");
		u.setPassword("104");
		u.setActivationCode("efgh");
		userDao.save(u);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		User u=new User();
		u.setUserId(1);
		u.setUserName("Ring");
		u.setEmail("13922924658@163.com");
		u.setPassword("1041233");
		u.setActivationCode("abcd123");
		userDao.update(u);
	}
	
	@Test
	@Rollback(false)
	public void testDelete() {
		userDao.delete(1);
	}
	
	@Test
	public void testGetPersonByEmail() {
		User u=userDao.getPersonByEmail("13922924658@qq.com");
		if(u==null)
			System.out.println("找不到此用户");
		else
			System.out.println("OK");
	}
}
