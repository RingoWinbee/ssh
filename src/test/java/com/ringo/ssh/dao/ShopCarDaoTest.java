package com.ringo.ssh.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.ShopCar;
import com.ringo.ssh.entity.User;

public class ShopCarDaoTest extends BaseTestCaseJunit44{

	@Resource(name="ShopCarDao")
	private IShopCarDao shopCarDao;	
	
	@Test
	@Rollback(false)
	public void testSave() {
		ShopCar s=new ShopCar();
		User u=new User();
		u.setUserId(2);
		s.setUsers(u);
		s.setGoodsCount(1);
		shopCarDao.save(s);
	}

	@Test
	@Rollback(false)
	public void testDelete() {
		shopCarDao.delete(2);
	}
}
