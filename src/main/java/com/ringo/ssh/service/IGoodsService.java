package com.ringo.ssh.service;

import java.util.List;
import java.util.Map;

import com.ringo.ssh.entity.Category;
import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.Reviews;
import com.ringo.ssh.entity.Reviews_User;
import com.ringo.ssh.entity.User;

public interface IGoodsService {
	public void saveGoods(Goods goods);
	public void updateGoods(Goods goods);
	public void deleteGoods(int goodsId);
	public List<Goods> getAllGoods();
	public List<Goods> getGoodsByName(String goodsName);
	public Goods getGoodsByGoodId(int goodsId);
	public List<Category> getGoodsByCategoryName(String categoryName);
	public List<Reviews_User> getReviewsByGoodsId(int goodsId);
}
