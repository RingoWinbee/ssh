package com.ringo.ssh.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.Order;
import com.ringo.ssh.entity.User;

public class OrderDaoTest extends BaseTestCaseJunit44{

	
	@Resource(name="OrderDao")
	private IOrdersDao OrderDao;	
	
	@Test
	@Rollback(false)
	public void testSave() {
		Order o=new Order();
		o.setConsigneeName("李卓岚");
		o.setOrderAddress("广东省东莞市");
		o.setOrderDate(new Date());
		o.setOrderPhone("18825510040");
		o.setOrderState(0);
		o.setTotalMoney(50.00);
		User u=new User();
		u.setUserId(1);
		o.setUsers(u);
		OrderDao.save(o);
	}

}
