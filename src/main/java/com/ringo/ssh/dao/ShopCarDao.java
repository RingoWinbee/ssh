package com.ringo.ssh.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.ShopCar;

@Repository("ShopCarDao")
public class ShopCarDao implements IShopCarDao{

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void save(ShopCar shopCar) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(shopCar);
	}

	@Override
	public void update(ShopCar shopCar) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(shopCar);
	}

	@Override
	public void delete(int shopCarId) {
		// TODO Auto-generated method stub
		ShopCar s_delete=(ShopCar)sessionFactory.getCurrentSession().get(ShopCar.class, shopCarId);
		sessionFactory.getCurrentSession().delete(s_delete);
	}

	@Override
	public ShopCar getShopCarByShopCarId(int shopCarId) {
		// TODO Auto-generated method stub
		return (ShopCar) sessionFactory.getCurrentSession().createQuery("from ShopCar where carId =?")
				.setParameter(0, shopCarId).uniqueResult();
	}

	@Override
	public ShopCar getShopCarByUserId(int userId) {
		// TODO Auto-generated method stub
		return (ShopCar) sessionFactory.getCurrentSession().createQuery("from ShopCar where userId =?")
				.setParameter(0, userId).uniqueResult();
	}

}
