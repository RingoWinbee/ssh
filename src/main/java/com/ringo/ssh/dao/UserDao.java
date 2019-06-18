package com.ringo.ssh.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.ringo.ssh.entity.User;

@Repository("UserDao")
public class UserDao implements IUserDao {

	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 保存对象
	 * 
	 * @param user
	 * @return null
	 */
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(user);
	}

	@Override
	public void delete(int userId) {
		// TODO Auto-generated method stub
		User u_delete=(User)sessionFactory.getCurrentSession().get(User.class, userId);
		sessionFactory.getCurrentSession().delete(u_delete);
	}

	/**
	 * 根据UserName来查找用户
	 * 
	 * @param String userName
	 * @return Person
	 */
	public User getPersonByEmail(String email) {
		//HQL语句也可以写成from User u where email =?
		return (User) sessionFactory.getCurrentSession().createQuery("select u from User u where email =?")
				.setParameter(0, email).uniqueResult();
	}
}
