package com.ringo.ssh.dao;

import java.util.List;
import com.ringo.ssh.entity.OrderDetails;

/**
 * OrderDetails 抽象持久层层接口
 * 
 * @author 李卓岚
 *
 */
public interface IOrderDetailsDao {

	public void save(OrderDetails orderDetails);

	public void update(OrderDetails orderDetails);

	public void delete(int orderDetailsId);

	// 查询
	public OrderDetails getOrderDetailsByOrderDetailsId(int orderDetailsId);

	public List<OrderDetails> getAllOrderDetails();
	
	public List<OrderDetails> getOrderDetailsyByOrderId(int orderId);
}
