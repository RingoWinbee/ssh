package com.ringo.ssh.service;

import java.util.List;

import com.ringo.ssh.entity.Category;

public interface ICategoryService {
	
	public void saveCategory(Category category);
	public void updateCategory(Category category);
	public void deleteCategory(int categoryId);
	public List<Category> getAllCategory();
}
