package com.ringo.ssh.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringo.ssh.dao.IShopCarDao;
import com.ringo.ssh.dao.IShopCarListDao;
import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.ShopCar;
import com.ringo.ssh.entity.ShopCarList;
import com.ringo.ssh.entity.ShopCarList_Goods;

@Service("ShopCarService")
public class ShopCarService implements IShopCarService {

	@Resource(name = "ShopCarDao")
	private IShopCarDao shopCarDao;

	@Resource(name = "ShopCarListDao")
	private IShopCarListDao shopCarListDao;

	@Resource
	private IGoodsService goodsService;

	@Override
	@Transactional
	public void saveShopCar(ShopCar shopCar) {
		// TODO Auto-generated method stub
		shopCarDao.save(shopCar);
	}

	@Override
	@Transactional
	public void updateShopCar(ShopCar shopCar) {
		// TODO Auto-generated method stub
		shopCarDao.update(shopCar);
	}

	@Override
	@Transactional
	public void deleteShopCar(int shopCarId) {
		// TODO Auto-generated method stub
		shopCarDao.delete(shopCarId);
	}

	@Override
	@Transactional
	public List<ShopCarList_Goods> getShopCarListByUserId(int userId) {
		// TODO Auto-generated method stub
		int shopCarId = shopCarDao.getShopCarByUserId(userId).getCarId();
		List<ShopCarList> scls = shopCarListDao.getShopCarListyByCarId(shopCarId);
		List<ShopCarList_Goods> sclds = new ArrayList<ShopCarList_Goods>();
		Goods g = null;
		ShopCarList_Goods scld = new ShopCarList_Goods();
		for (ShopCarList scl : scls) {
			g = goodsService.getGoodsByGoodId(scl.getGoods().getGoodsId());
			scld.setGoodsId(g.getGoodsId());
			scld.setGoodsCount(scl.getGoodsCount());
			scld.setGoodsName(g.getGoodsName());
			scld.setGoodsPtice(g.getGoodsRealPrice());
			scld.setGooodsImg(g.getGoodsImg());
			scld.setShopCarListId(scl.getShopCarListId());
			sclds.add(scld);
		}
		return sclds;
	}
}
