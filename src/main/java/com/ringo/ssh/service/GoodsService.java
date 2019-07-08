package com.ringo.ssh.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ringo.ssh.dao.ICategoryDao;
import com.ringo.ssh.dao.IGoodsDao;
import com.ringo.ssh.dao.IOrderDetailsDao;
import com.ringo.ssh.dao.IOrdersDao;
import com.ringo.ssh.dao.IReviewsDao;
import com.ringo.ssh.entity.Category;
import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.OrderDetails;
import com.ringo.ssh.entity.Reviews;
import com.ringo.ssh.entity.Reviews_User;
import com.ringo.ssh.entity.User;

@Service("GoodsService")
public class GoodsService implements IGoodsService{

	@Resource(name="GoodsDao")
	private IGoodsDao goodsDao;
	
	@Resource(name="CategoryDao")
	private ICategoryDao categoryDao;
	
	@Resource(name="OrderDetailsDao")
	private IOrderDetailsDao orderDetailsDao;
	
	@Resource(name="ReviewsDao")
	private IReviewsDao reviewsDao;
	
	@Resource(name="OrderDao")
	private IOrdersDao orderDao;

	@Override
	@Transactional
	public void saveGoods(Goods goods) {
		// TODO Auto-generated method stub
		goodsDao.save(goods);
	}

	@Override
	@Transactional
	public void updateGoods(Goods goods) {
		// TODO Auto-generated method stub
		goodsDao.update(goods);
	}

	@Override
	@Transactional
	public void deleteGoods(int goodsId) {
		// TODO Auto-generated method stub
		goodsDao.delete(goodsId);
	}

	@Override
	@Transactional
	public List<Goods> getAllGoods() {
		// TODO Auto-generated method stub
		return goodsDao.getGoodsByGoodsName("");
	}

	@Override
	@Transactional
	public List<Goods> getGoodsByName(String goodsName) {
		// TODO Auto-generated method stub
		return goodsDao.getGoodsByGoodsName(goodsName);
	}

	@Override
	@Transactional
	public Goods getGoodsByGoodId(int goodsId) {
		// TODO Auto-generated method stub
		return goodsDao.getGoodsByGoodId(goodsId);
	}
	
	@Override
	@Transactional
	public List<Category> getGoodsByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryByCategoryName(categoryName);
	}
	
	@Override
	@Transactional
	public List<Reviews_User> getReviewsByGoodsId(int goodsId) {
		// TODO Auto-generated method stub
		List<OrderDetails> ods=orderDetailsDao.getOrderDetailsyByGoodsId(goodsId);
		List<Integer> odIds=new ArrayList<Integer>();
		List<Integer> orderIds=new ArrayList<Integer>();
		for(OrderDetails od:ods) {
			odIds.add(od.getOrderDetailsId());
			orderIds.add(od.getOrder().getOrderId());
		}
		List<Reviews> rs=new ArrayList<Reviews>();
		for(int odId:odIds) {
			rs.add(reviewsDao.getReviewsByOrderDetailsId(odId));
		}
		List<User> us=new ArrayList<User>();
		for(int orderId:orderIds) {
			us.add(orderDao.getOrderByOrderId(orderId).getUsers());
		}
		Reviews_User ru=null;
		List<Reviews_User> rus=new ArrayList<Reviews_User>();
		for(int i=0;i<rs.size();i++) {
			ru=new Reviews_User();
			ru.setUserName(us.get(i).getUserName());
			ru.setReviewsInfo(rs.get(i).getReviewsInfo());
			ru.setReviewsDate(rs.get(i).getReviewsDate());
			rus.add(ru);
		}
		return rus;
	}
}
