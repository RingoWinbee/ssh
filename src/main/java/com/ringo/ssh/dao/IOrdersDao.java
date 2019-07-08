package com.ringo.ssh.dao;

import java.util.List;

import com.ringo.ssh.entity.Order;

/**
 * Orders抽象持久层层接口:
 * 		
 * @author 李卓岚
 *
 */
public interface IOrdersDao {

	public int save(Order order);
	public void update(Order order);
	public void delete(int orderId);
	//查询
	public Order getOrderByOrderId(int orderId);
	public List<Order> getOrderByUserId(int userId);
}
