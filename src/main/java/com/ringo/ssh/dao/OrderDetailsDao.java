package com.ringo.ssh.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.ringo.ssh.entity.OrderDetails;

@Repository("OrderDetailsDao")
public class OrderDetailsDao implements IOrderDetailsDao{

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void save(OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(orderDetails);
	}

	@Override
	public void update(OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(orderDetails);
	}

	@Override
	public void delete(int orderDetailsId) {
		// TODO Auto-generated method stub
		OrderDetails o_delete=(OrderDetails)sessionFactory.getCurrentSession().get(OrderDetails.class,orderDetailsId);
		sessionFactory.getCurrentSession().delete(o_delete);
	}

	@Override
	public OrderDetails getOrderDetailsByOrderDetailsId(int orderDetailsId) {
		// TODO Auto-generated method stub
		return (OrderDetails) sessionFactory.getCurrentSession().createQuery("from OrderDetails where orderDetailsId =?")
				.setParameter(0, orderDetailsId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetails> getAllOrderDetails() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from OrderDetails")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetails> getOrderDetailsyByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return  sessionFactory.getCurrentSession().createQuery("from OrderDetails where orderId =?")
				.setParameter(0, orderId).list();
	}
}
