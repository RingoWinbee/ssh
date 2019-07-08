package com.ringo.ssh.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringo.ssh.dao.IShopCarListDao;
import com.ringo.ssh.entity.ShopCarList;

@Service("ShopCarListService")
public class ShopCarListService implements IShopCarListService{

	@Resource(name = "ShopCarListDao")
	IShopCarListDao shopCarListDao;
	
	@Override
	@Transactional
	public void saveShopCarList(ShopCarList shopCarList) {
		// TODO Auto-generated method stub
		shopCarListDao.save(shopCarList);
	}

	@Override
	@Transactional
	public void updateShopCarList(ShopCarList shopCarList) {
		// TODO Auto-generated method stub
		shopCarListDao.update(shopCarList);
	}

	@Override
	@Transactional
	public void deleteShopCarList(int shopCarListId) {
		// TODO Auto-generated method stub
		shopCarListDao.delete(shopCarListId);
	}

	@Override
	@Transactional
	public ShopCarList getShopCarListyByGoodsId(int goodsId) {
		// TODO Auto-generated method stub
		return shopCarListDao.getShopCarListyByGoodsId(goodsId);
	}
	
	@Override
	@Transactional
	public ShopCarList getShopCarListyByShopCarListId(int shopCarListId) {
		// TODO Auto-generated method stub
		return shopCarListDao.getShopCarListyByShopCarListId(shopCarListId);
	}
}
