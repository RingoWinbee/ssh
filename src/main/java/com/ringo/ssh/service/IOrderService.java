package com.ringo.ssh.service;

import java.util.List;
import com.ringo.ssh.entity.Order;

public interface IOrderService {
	
	public int saveOrder(Order order);
	public void updateOrder(Order order);
	public void deleteOrder(int orderId);
	//public List<ShopCarList_Goods> getShopCarListByUserId(int UserId);
	public Order getOrderByOrderId(int orderId);
	public List<Order> getOrdersByUserId(int userId);
}
