package com.ringo.ssh.dao;

import java.util.List;
import com.ringo.ssh.entity.Category;
/**
 * Category抽象持久层层接口
 * 		
 * @author 李卓岚
 *
 */
public interface ICategoryDao {
	
		public void save(Category category);
		public void update(Category category);
		public void delete(int categoryId);
		//查询
		public Category getGoodsBycategoryId(int categoryId);
		public List<Category> getAllCategory();
		public List<Category> getCategoryByCategoryName(String categoryName);
}
