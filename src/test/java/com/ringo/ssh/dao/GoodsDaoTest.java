package com.ringo.ssh.dao;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.ringo.ssh.entity.Category;
import com.ringo.ssh.entity.Goods;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class GoodsDaoTest extends BaseTestCaseJunit44 {

	@Resource(name="GoodsDao")
	private IGoodsDao goodsDao;	
	

	@Test
	@Rollback(false)
	public void testSave() {
		Goods goods=new Goods();
		goods.setGoodsName("长袖");
		goods.setGoodsInfo("街舞专业");
		goods.setGoodsPrice(10.00);
		goods.setGoodsRealPrice(10.00);
		goods.setGoodsDate(new Date());
		Category c=new Category();
		c.setCategoryId(1);
		goods.setCategory(c);
		goodsDao.save(goods);
	}
	
	@Test
	@Rollback(false)
	public void testDelete() {
		goodsDao.delete(2);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		Goods g=goodsDao.getGoodsByGoodId(3);
		g.setGoodsName("长袖");
		goodsDao.update(g);
	}
	
	@Test
	@Rollback(false)
	public void testGetGoodsById() {
		Goods g=goodsDao.getGoodsByGoodId(3);
		System.out.println(g.getGoodsName());
	}

	@Test
	@Rollback(false)
	public void testGetGoodsByGoodsName() {
		List<Goods> list=goodsDao.getGoodsByGoodsName("袖");
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"goods","shopCar"});  //此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(list,jsonConfig);//将集合转换为json格式
		System.out.println(jsonArray2.toString());

	}
}
