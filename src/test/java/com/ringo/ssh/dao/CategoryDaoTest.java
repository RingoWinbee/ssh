package com.ringo.ssh.dao;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.ringo.ssh.entity.Category;

public class CategoryDaoTest extends BaseTestCaseJunit44{

	@Resource(name="CategoryDao")
	private ICategoryDao categoryDao;	
	
	@Test
	@Rollback(false)
	public void testSave() {
		Category c=new Category();
		c.setCategoryName("服饰");
		categoryDao.save(c);
	}

	@Test
	@Rollback(false)	
	public void testDelete() {
		categoryDao.delete(1);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		Category c=categoryDao.getGoodsBycategoryId(2);
		c.setCategoryName("食品");
		categoryDao.update(c);
	}
	
	@Test
	@Rollback(false)
	public void testGetCategoryById() {
		Category c=categoryDao.getGoodsBycategoryId(2);
		System.out.println(c.getCategoryName());
	}
}
