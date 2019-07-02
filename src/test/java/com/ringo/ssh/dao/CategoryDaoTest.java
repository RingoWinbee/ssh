package com.ringo.ssh.dao;

import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.ringo.ssh.entity.Category;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

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
	
	@Test
	@Rollback(false)
	public void testGetCategoryByName() {
		List<Category> list=categoryDao.getCategoryByCategoryName("食品");
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"category"});  //此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(list,jsonConfig);//将集合转换为json格式
		System.out.println(jsonArray2.toString());
	}
}
