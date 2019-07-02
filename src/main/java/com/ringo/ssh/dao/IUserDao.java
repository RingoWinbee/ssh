package com.ringo.ssh.dao;

import com.ringo.ssh.entity.User;

/**
 * User抽象持久层层接口:
 * 		
 * @author 李卓岚
 *
 */
public interface IUserDao {
	public void save(User user);
	public void update(User user);
	public void delete(int userId);
	//查询
	public User getPersonByEmail(String email);
	public User getPersonByUserId(int userId);
}
