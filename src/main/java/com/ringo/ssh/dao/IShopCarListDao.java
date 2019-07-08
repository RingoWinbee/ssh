package com.ringo.ssh.dao;

import java.util.List;
import com.ringo.ssh.entity.ShopCarList;

/**
 * ShopCarList 抽象持久层层接口
 * 
 * @author 李卓岚
 *
 */

public interface IShopCarListDao {

	public void save(ShopCarList shopCarList);

	public void update(ShopCarList shopCarList);

	public void delete(int shopCarListId);

	// 查询
	public ShopCarList getShopCarListByshopCarListId(int ShopCarListId);

	public List<ShopCarList> getAllShopCarList();

	public List<ShopCarList> getShopCarListyByCarId(int carId);
	
	public ShopCarList getShopCarListyByGoodsId(int goodsId);
	
	public ShopCarList getShopCarListyByShopCarListId(int shopCarListId);
}
