package com.ringo.ssh.dao;

import java.util.List;

import com.ringo.ssh.entity.ShopCar;

/**
 * ShopCar抽象持久层层接口:
 * 		
 * @author 李卓岚
 *
 */
public interface IShopCarDao {
	
	public void save(ShopCar shopCar);
	public void update(ShopCar shopCar);
	public void delete(int shopCarId);
	//查询
	public ShopCar getShopCarByShopCarId(int shopCarId);
	public ShopCar getShopCarByUserId(int userId);
}
