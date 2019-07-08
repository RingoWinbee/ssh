package com.ringo.ssh.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringo.ssh.dao.IOrdersDao;
import com.ringo.ssh.dao.IShopCarDao;
import com.ringo.ssh.entity.Order;

@Service("OrderService")
public class OrderService implements IOrderService{

	@Resource(name = "OrderDao")
	private IOrdersDao orderDao;
	
	@Override
	@Transactional
	public int saveOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.save(order);
	}

	@Override
	@Transactional
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.update(order);
	}

	@Override
	@Transactional
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		orderDao.delete(orderId);
	}

	@Override
	@Transactional
	public Order getOrderByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return orderDao.getOrderByOrderId(orderId);
	}

	@Override
	@Transactional
	public List<Order> getOrdersByUserId(int userId) {
		// TODO Auto-generated method stub
		return orderDao.getOrderByUserId(userId);
	}

}
