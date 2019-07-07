package com.ringo.ssh.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.ShopCar;
import com.ringo.ssh.entity.ShopCarList;
import com.ringo.ssh.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class ShopCarDaoTest extends BaseTestCaseJunit44{

	@Resource(name="ShopCarDao")
	private IShopCarDao shopCarDao;	
	
	@Test
	@Rollback(false)
	public void testSave() {
		ShopCar s=new ShopCar();
		User u=new User();
		u.setUserId(1);
		s.setUsers(u);
		s.setGoodsCount(1);
		shopCarDao.save(s);
	}

	@Test
	@Rollback(false)
	public void testDelete() {
		shopCarDao.delete(2);
	}
	
	@Test
	@Rollback(false)
	public void testGetByCarId() {
	ShopCar sc=shopCarDao.getShopCarByShopCarId(2);
	JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
	jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
	jsonConfig.setExcludes(new String[]{"shopCar","goods","users"});  //此处是亮点，只要将所需忽略字段加到数组中即可
	JSONArray jsonArray2 = JSONArray.fromObject(sc,jsonConfig);//将集合转换为json格式
	System.out.println(jsonArray2);
	//[{"carId":1,"goodsCount":1,"shopCarList":[{"goodsCount":1,"shopCarListId":1}]}]
	/**查到的信息
	 * shopCar(购物车本身的信息)
	 * shopCarList(属于该购物车的购物车列表信息,没有商品信息)
	 */
	}
}
