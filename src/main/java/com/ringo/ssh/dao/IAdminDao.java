package com.ringo.ssh.dao;

import com.ringo.ssh.entity.Admin;
import com.ringo.ssh.entity.User;

public interface IAdminDao {
	public void save(Admin admin);
	public void update(Admin admin);

	//查询
	public Admin getPersonByEmail(String email);
	
}
