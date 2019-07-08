package com.ringo.ssh.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
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
	 * @return User
	 */
	public User getPersonByEmail(String email) {
		//HQL语句也可以写成from User u where email =?
		return (User) sessionFactory.getCurrentSession().createQuery("from User where email =?")
				.setParameter(0, email).uniqueResult();
	}
	
	/**
	 * 根据userId来查找用户
	 * 
	 * @param String userName
	 * @return User
	 */
	public User getPersonByUserId(int userId) {
		//HQL语句也可以写成from User u where email =?
		return (User) sessionFactory.getCurrentSession().createQuery("from User where userId =?")
				.setParameter(0, userId).uniqueResult();
	}

	/**
	 * 根据userName模糊查找用户
	 */
	@Override
	public List<User> getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery(" from User this WHERE this.userName like :userName ")
                .setParameter("userName", "%"+userName+"%")
                .list();

	}

	@Override
	public List<User> listUser(int offset,int pageSize) {
		Query query = sessionFactory.getCurrentSession().createQuery("from User user order by user.id");
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
		
	}

	@Override
	public int countUser() {
	    return Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from User").list().get(0).toString());
	}
}
