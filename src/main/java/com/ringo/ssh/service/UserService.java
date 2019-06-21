package com.ringo.ssh.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ringo.ssh.dao.IUserDao;
import com.ringo.ssh.entity.User;
import com.ringo.ssh.exception.MyException;

@Service("UserService")
public class UserService implements IUserService{
	
	@Resource(name="UserDao")
	private IUserDao userDao;
	
	@Override
	@Transactional
	public void saveUser(User user) {
		userDao.save(user);
	}
	
	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	@Transactional
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		userDao.delete(userId);
	}

	@Override
	@Transactional(readOnly=true)
	public int checkSignIn(String email,String password) throws MyException {
		// TODO Auto-generated method stub
		User u=(User)userDao.getPersonByEmail(email);
		if(u==null)
			throw new MyException("该用户名不存在！");
		else if(!password.equals(u.getPassword()))
			throw new MyException("密码错误！");
		else
		return 1;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isHasUser(String email){
		// TODO Auto-generated method stub
		User u=(User)userDao.getPersonByEmail(email);
		if(u!=null)
			return true;
		else
			return false;
	}
}
