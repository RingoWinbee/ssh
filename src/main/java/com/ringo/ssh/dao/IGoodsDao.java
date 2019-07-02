package com.ringo.ssh.dao;

import java.util.List;

import com.ringo.ssh.entity.Goods;

/**
 * Good抽象持久层层接口:
 * 		
 * @author 李卓岚
 *
 */
public interface IGoodsDao {
	public void save(Goods goods);
	public void update(Goods goods);
	public void delete(int goodsId);
	//查询
	public Goods getGoodsByGoodId(int goodsId);
	public List<Goods> getGoodsByGoodsName(String goodsName);
}
