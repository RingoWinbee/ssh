package com.ringo.ssh.service;

import java.util.List;

import com.ringo.ssh.entity.Category;
import com.ringo.ssh.entity.Goods;

public interface IGoodsService {
	public void saveGoods(Goods goods);
	public void updateGoods(Goods goods);
	public void deleteGoods(int goodsId);
	public List<Goods> getAllGoods();
	public List<Goods> getGoodsByName(String goodsName);
	public Goods getGoodsByGoodId(int goodsId);
	public List<Category> getGoodsByCategoryName(String categoryName);
}
