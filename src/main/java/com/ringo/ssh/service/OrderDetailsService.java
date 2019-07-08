package com.ringo.ssh.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringo.ssh.dao.IGoodsDao;
import com.ringo.ssh.dao.IOrderDetailsDao;
import com.ringo.ssh.dao.IOrdersDao;
import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.Order;
import com.ringo.ssh.entity.OrderDetails;
import com.ringo.ssh.entity.Order_Goods;

@Service("OrderDetailsService")
public class OrderDetailsService implements IOrderDetailsService{

	@Resource(name = "OrderDetailsDao")
	private IOrderDetailsDao orderDetailsDao;
	
	@Resource(name = "OrderDao")
	private IOrdersDao orderDao;
	
	@Resource(name = "GoodsDao")
	private IGoodsDao goodsDao;
	
	@Override
	@Transactional
	public void saveOrderDetails(OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		orderDetailsDao.save(orderDetails);
	}

	@Override
	@Transactional
	public void updateOrderDetails(OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		orderDetailsDao.update(orderDetails);
	}

	@Override
	@Transactional
	public void deleteOrderDetails(int orderDetailsId) {
		// TODO Auto-generated method stub
		orderDetailsDao.delete(orderDetailsId);
	}

	@Override
	@Transactional
	public List<Order_Goods> getOrderDetailsByUserId(int userId) {
		// TODO Auto-generated method stub
		List<Order> os=orderDao.getOrderByUserId(userId);
		List<Order_Goods> ogs=new ArrayList<Order_Goods>();
		Order_Goods og=new Order_Goods();
		List<Goods> goods=new ArrayList<Goods>();
		List<Integer> goodsCount=new ArrayList<Integer>();
		List<OrderDetails> ods=null;
		for(Order o:os) {
			og.setConsigneeName(o.getConsigneeName());
			og.setOrderAddress(o.getOrderAddress());
			og.setOrderDate(o.getOrderDate());
			og.setOrderId(o.getOrderId());
			og.setOrderPhone(o.getOrderPhone());
			og.setOrderState(o.getOrderState());
			og.setTotalMoney(o.getTotalMoney());
			ods=orderDetailsDao.getOrderDetailsyByOrderId(o.getOrderId());
			for(OrderDetails od:ods) {
				goods.add(od.getGoods());
				goodsCount.add(od.getGoodsCount());
			}
			og.setGoods(goods);
			og.setGoodsCount(goodsCount);
			ogs.add(og);
		}
		return ogs;
	}

	@Override
	@Transactional
	public OrderDetails getOrderDetailsByOrderDetailsId(int orderDetailsId) {
		// TODO Auto-generated method stub
		return orderDetailsDao.getOrderDetailsByOrderDetailsId(orderDetailsId);
	}

}
