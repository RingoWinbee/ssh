package com.ringo.ssh.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.Order;
import com.ringo.ssh.entity.OrderDetails;

public class OrderDetailsDaoTest extends BaseTestCaseJunit44{

	@Resource(name="OrderDetailsDao")
	private IOrderDetailsDao OrderDetailsDao;	

	@Test
	@Rollback(false)
	public void testSave() {
		OrderDetails od=new OrderDetails();
		Goods g=new Goods();
		g.setGoodsId(1);
		od.setGoods(g);
		od.setGoodsCount(2);
		Order o=new Order();
		o.setOrderId(1);
		od.setOrder(o);
		od.setSumMoney(50.00);
		OrderDetailsDao.save(od);
	}

}
