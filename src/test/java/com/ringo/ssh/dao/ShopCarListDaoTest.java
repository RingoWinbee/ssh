package com.ringo.ssh.dao;

import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.Category;
import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.ShopCar;
import com.ringo.ssh.entity.ShopCarList;
import com.ringo.ssh.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class ShopCarListDaoTest extends BaseTestCaseJunit44{

	@Resource(name="ShopCarListDao")
	private IShopCarListDao shopCarListDao;	

	@Test
	@Rollback(false)
	public void testSave() {
		ShopCar s=new ShopCar();
		User u=new User();
		u.setUserId(2);
		s.setUsers(u);
		s.setCarId(1);
		Goods g=new Goods();
		g.setGoodsId(2);
		ShopCarList sc=new ShopCarList();
		sc.setShopCar(s);
		sc.setGoods(g);
		sc.setGoodsCount(1);
		shopCarListDao.save(sc);
	}

	@Test
	@Rollback(false)
	public void testGetAllShopCarList() {
		List<ShopCarList> list=shopCarListDao.getAllShopCarList();
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"shopCar","category","users","shopCarList","orderDetails"});  //此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(list,jsonConfig);//将集合转换为json格式
		System.out.println(jsonArray2);
		//[{"goods":{"goodsDate":{"date":7,"day":0,"hours":15,"minutes":38,"month":6,"nanos":0,"seconds":41,"time":1562485121000,"timezoneOffset":-480,"year":119},"goodsId":1,"goodsImg":"","goodsInfo":"街舞专业","goodsName":"短袖","goodsPrice":10,"goodsRealPrice":10,"goodsState":0,"goodsStock":0},"goodsCount":1,"shopCarListId":1}]
		/**查询到的信息
		 * shopCarList(购物车列表的基本信息)
		 * goods(没有商品分类)
		 */
	}
	
	@Test
	@Rollback(false)
	public void testGetShopCarListByCarId() {
		List<ShopCarList> list=shopCarListDao.getShopCarListyByCarId(1);
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"shopCar","category","users","shopCarList","orderDetails"});  //此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(list,jsonConfig);//将集合转换为json格式
		System.out.println(jsonArray2);
		//[{"goods":{"goodsDate":{"date":7,"day":0,"hours":15,"minutes":38,"month":6,"nanos":0,"seconds":41,"time":1562485121000,"timezoneOffset":-480,"year":119},"goodsId":1,"goodsImg":"","goodsInfo":"街舞专业","goodsName":"短袖","goodsPrice":10,"goodsRealPrice":10,"goodsState":0,"goodsStock":0},"goodsCount":1,"shopCarListId":1}]
		/**查询到的信息
		 * shopCarList(购物车列表的基本信息)
		 * goods(没有商品分类)
		 */
	}
	
	@Test
	@Rollback(false)
	public void testDeleteShopCarListById() {
		shopCarListDao.delete(2);
	}
}
