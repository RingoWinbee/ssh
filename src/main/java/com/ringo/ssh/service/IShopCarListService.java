package com.ringo.ssh.service;

import com.ringo.ssh.entity.ShopCarList;

public interface IShopCarListService {

	public void saveShopCarList(ShopCarList shopCarList);
	public void updateShopCarList(ShopCarList shopCarList);
	public void deleteShopCarList(int shopCarListId);
	
	public ShopCarList getShopCarListyByGoodsId(int goodsId);
	public ShopCarList getShopCarListyByShopCarListId(int shopCarListId);
}
