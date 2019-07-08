package com.ringo.ssh.dao;

import java.util.List;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.ringo.ssh.entity.Order;

@Repository("OrderDao")
public class OrderDao implements IOrdersDao{

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public int save(Order order) {
		// TODO Auto-generated method stub
		return Integer.parseInt((String)sessionFactory.getCurrentSession().save(order));
	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(order);
	}

	@Override
	public void delete(int orderId) {
		// TODO Auto-generated method stub
		Order o_delete=(Order)sessionFactory.getCurrentSession().get(Order.class, orderId);
		sessionFactory.getCurrentSession().delete(o_delete);
	}

	@Override
	public Order getOrderByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return (Order) sessionFactory.getCurrentSession().createQuery("from Order where orderId =?")
				.setParameter(0, orderId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrderByUserId(int userId) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Order where userId =?")
				.setParameter(0, userId).list();
	}

}
