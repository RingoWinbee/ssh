package com.ringo.ssh.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ringo.ssh.entity.Category;

@Repository("CategoryDao")
public class CategoryDao implements ICategoryDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(category);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(category);
	}

	@Override
	public void delete(int categoryId) {
		// TODO Auto-generated method stub
		Category c_delete=(Category)sessionFactory.getCurrentSession().get(Category.class,categoryId);
		sessionFactory.getCurrentSession().delete(c_delete);
	}

	@Override
	public Category getGoodsBycategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return (Category) sessionFactory.getCurrentSession().createQuery("from Category where categoryId =?")
				.setParameter(0, categoryId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Category")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoryByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		String hql="from Category where categoryName like :param";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setString("param", "%"+categoryName+"%").list();
	}

}
