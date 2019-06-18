package com.ringo.ssh.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringo.ssh.dao.UserDao;
import com.ringo.ssh.entity.User;

@Service("UserService")
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	@Transactional
	public void saveUser(User user) {
		userDao.save(user);
	}
	
	@Transactional
	public void updateUser(User user) {
		userDao.update(user);
	}
}
