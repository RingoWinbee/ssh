package com.ringo.ssh.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringo.ssh.dao.ICategoryDao;
import com.ringo.ssh.dao.IGoodsDao;
import com.ringo.ssh.entity.Category;
import com.ringo.ssh.entity.Goods;

@Service("GoodsService")
public class GoodsService implements IGoodsService{

	@Resource(name="GoodsDao")
	private IGoodsDao goodsDao;
	
	@Resource(name="CategoryDao")
	private ICategoryDao categoryDao;
	
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
}
