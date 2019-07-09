package com.ringo.ssh.dao;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.Admin;

public class AdminDaoTest extends BaseTestCaseJunit44{

	//@Autowired
	@Resource(name="AdminDao")
	private AdminDao adminDao;	
	
	@Test
	@Rollback(false)
	public void testSave() {
		Admin ad = new Admin();
		ad.setUserName("RingoAdmin");
		ad.setEmail("13922924658@163.com");
		ad.setPassword("123");
		adminDao.save(ad);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		Admin ad = new Admin();
		ad.setAdminId(1);
		ad.setUserName("RingoAdmin123");
		ad.setEmail("13922924658@163.com");
		ad.setPassword("123456");
		adminDao.update(ad);
	}
	
	
	
}
