package com.ringo.ssh.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ringo.ssh.entity.Admin;
import com.ringo.ssh.entity.User;

@Repository("AdminDao")
public class AdminDao implements IAdminDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Admin admin) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(admin); 
	}

	@Override
	public void update(Admin admin) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(admin);
	}

	@Override
	public Admin getPersonByEmail(String email) {
		// TODO Auto-generated method stub
		return (Admin) sessionFactory.getCurrentSession().createQuery("from Admin where email =?")
				.setParameter(0, email).uniqueResult();
	}



}
