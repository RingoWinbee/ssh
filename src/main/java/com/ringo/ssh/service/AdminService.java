package com.ringo.ssh.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringo.ssh.dao.IAdminDao;
import com.ringo.ssh.entity.Admin;
import com.ringo.ssh.entity.User;
import com.ringo.ssh.exception.MyException;

@Service("AdminService")
public class AdminService implements IAdminService {

	@Resource(name="AdminDao")
	private IAdminDao adminDao;
	
	@Override
	@Transactional(readOnly=true)
	public int checkSignIn(String email, String password) throws MyException {
		// TODO Auto-generated method stub
		Admin ad = (Admin)adminDao.getPersonByEmail(email);
		if(ad == null)
			throw new MyException("该用户名不存在！");
		else if(!password.equals(ad.getPassword()))
			throw new MyException("密码错误！");
		else 
			return ad.getAdminId();
	}

}
