package com.ringo.ssh.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import com.ringo.ssh.dao.BaseTestCaseJunit44;
import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.ShopCarList;
import com.ringo.ssh.entity.ShopCarList_Goods;
import net.sf.json.JSONArray;

public class ShopCarServiceTest extends BaseTestCaseJunit44{

//	@Resource
//	private IShopCarService shopCarService;
//	
//	@Resource
//	private IGoodsService goodsService;
	
//	@Test
	//public void testgetShopCarListByUserId() {
//		List<ShopCarList> scls=shopCarService.getShopCarListByUserId(2);
//		List<ShopCarList_Goods> sclds=new ArrayList<ShopCarList_Goods>();
//		Goods g=null;
//		ShopCarList_Goods scld=new ShopCarList_Goods();
//		for(ShopCarList scl:scls) {
//			g=goodsService.getGoodsByGoodId(scl.getGoods().getGoodsId());
//				scld.setGoodsId(g.getGoodsId());
//				scld.setGoodsCount(scl.getGoodsCount());
//				scld.setGoodsName(g.getGoodsName());
//				scld.setGoodsPtice(g.getGoodsRealPrice());
//				scld.setGooodsImg(g.getGoodsImg());
//				scld.setShopCarListId(scl.getShopCarListId());
//				sclds.add(scld);
//		}
//		JSONArray jsonArray2 = JSONArray.fromObject(sclds);// 将集合转换为json格式
//		System.out.println(jsonArray2);
//	}

}
