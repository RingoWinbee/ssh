package com.ringo.ssh.dao;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.ringo.ssh.entity.Category;
import com.ringo.ssh.entity.Goods;

public class GoodsDaoTest extends BaseTestCaseJunit44 {

	@Resource(name="GoodsDao")
	private IGoodsDao goodsDao;	
	

	@Test
	@Rollback(false)
	public void testSave() {
		Goods goods=new Goods();
		goods.setGoodsName("短袖");
		goods.setGoodsInfo("街舞专业");
		goods.setGoodsPrice(10.00);
		goods.setGoodsRealPrice(10.00);
		goods.setGoodsDate(new Date());
		Category c=new Category();
		c.setCategoryId(3);
		goods.setCategory(c);
		goodsDao.save(goods);
	}
	
	@Test
	@Rollback(false)
	public void testDelete() {
		goodsDao.delete(8);
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
		List<Goods> list=goodsDao.getGoodsByGoodsName("薯条");
		for(Goods g:list) {
			System.out.println(g.getGoodsName());
		}

	}
}
