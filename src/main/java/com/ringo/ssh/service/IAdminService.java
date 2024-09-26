package com.ringo.ssh.service;

import com.ringo.ssh.exception.MyException;

public interface IAdminService {

	public int checkSignIn(String email,String password)throws MyException;
	
}
