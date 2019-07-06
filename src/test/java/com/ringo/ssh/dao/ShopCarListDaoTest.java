package com.ringo.ssh.dao;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.ShopCar;
import com.ringo.ssh.entity.ShopCarList;
import com.ringo.ssh.entity.User;

public class ShopCarListDaoTest extends BaseTestCaseJunit44{

	@Resource(name="ShopCarListDao")
	private IShopCarListDao shopCarListDao;	

	@Test
	@Rollback(false)
	public void testSave() {
		ShopCar s=new ShopCar();
		User u=new User();
		u.setUserId(2);
		s.setUsers(u);
		s.setCarId(3);
		Goods g=new Goods();
		g.setGoodsId(2);
		ShopCarList sc=new ShopCarList();
		sc.setShopCar(s);
		sc.setGoods(g);
		sc.setGoodsCount(1);
		shopCarListDao.save(sc);
	}

}
