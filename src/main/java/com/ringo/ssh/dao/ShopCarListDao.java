package com.ringo.ssh.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ringo.ssh.entity.ShopCarList;

@Repository("ShopCarListDao")
public class ShopCarListDao implements IShopCarListDao{

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void save(ShopCarList shopCarList) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(shopCarList);
	}

	@Override
	public void update(ShopCarList shopCarList) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(shopCarList);
	}

	@Override
	public void delete(int shopCarListId) {
		// TODO Auto-generated method stub
		ShopCarList s_delete=(ShopCarList)sessionFactory.getCurrentSession().get(ShopCarList.class,shopCarListId);
		sessionFactory.getCurrentSession().delete(s_delete);
	}

	@Override
	public ShopCarList getShopCarListByshopCarListId(int ShopCarListId) {
		// TODO Auto-generated method stub
		return (ShopCarList) sessionFactory.getCurrentSession().createQuery("from ShopCarList where shopCarListId =?")
				.setParameter(0, ShopCarListId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopCarList> getAllShopCarList() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from ShopCarList")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopCarList> getShopCarListyByCarId(int carId) {
		// TODO Auto-generated method stub
		return  sessionFactory.getCurrentSession().createQuery("from ShopCarList where carId =?")
				.setParameter(0, carId).list();
	}
}
