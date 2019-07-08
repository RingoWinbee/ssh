package com.ringo.ssh.service;

import java.util.List;

import com.ringo.ssh.entity.ShopCar;
import com.ringo.ssh.entity.ShopCarList;
import com.ringo.ssh.entity.ShopCarList_Goods;

public interface IShopCarService {
	
	public void saveShopCar(ShopCar shopCar);
	public void updateShopCar(ShopCar shopCar);
	public void deleteShopCar(int shopCarId);
	public List<ShopCarList_Goods> getShopCarListByUserId(int UserId);
	public ShopCar getShopCarByUserId(int userId);
}
