package com.ringo.ssh.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringo.ssh.dao.ICategoryDao;
import com.ringo.ssh.entity.Category;

@Service("CategoryService")
public class CategoryService implements ICategoryService{

	@Resource(name="CategoryDao")
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional
	public void saveCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	@Override
	@Transactional
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}

	@Override
	@Transactional
	public void deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		categoryDao.delete(categoryId);
	}

	@Override
	@Transactional
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryDao.getAllCategory();
	}
	
}
