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
		u.setUserId(1);
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
		jsonConfig.setExcludes(new String[]{"category"});  //此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(list,jsonConfig);//将集合转换为json格式
		System.out.println(jsonArray2);
	}
}