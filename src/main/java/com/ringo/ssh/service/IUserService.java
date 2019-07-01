package com.ringo.ssh.service;

import com.ringo.ssh.entity.User;
import com.ringo.ssh.exception.MyException;

public interface IUserService {
	public void saveUser(User user);
	public void updateUser(User user);
	public void deleteUser(int userId);
	public int checkSignIn(String email,String password)throws MyException;
	public boolean isHasUser(String email);
	public User getUserByUserId(int userId);
}
