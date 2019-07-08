package com.ringo.ssh.service;

import java.util.List;

import com.ringo.ssh.entity.OrderDetails;
import com.ringo.ssh.entity.Order_Goods;

public interface IOrderDetailsService {

	public void saveOrderDetails(OrderDetails orderDetails);
	public void updateOrderDetails(OrderDetails orderDetails);
	public void deleteOrderDetails(int orderDetailsId);
	
	public List<Order_Goods> getOrderDetailsByUserId(int userId);
	public OrderDetails getOrderDetailsByOrderDetailsId(int orderDetailsId);
}
