package com.ringo.ssh.dao;

import java.util.List;

import com.ringo.ssh.entity.User;

/**
 * 抽象持久层层接口:
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
	//用户名模糊查询
	public List<User> getUserByUserName(String userName);
	public List<User> listUser(int offset,int pageSize);
	//返回用户总数
	public int countUser();
}
