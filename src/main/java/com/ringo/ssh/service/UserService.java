package com.ringo.ssh.service;

import java.util.List;

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
	@Transactional
	public User getUserByUserId(int userId) {
		// TODO Auto-generated method stub
		return userDao.getPersonByUserId(userId);
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
			return u.getUserId();
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

	@Override
	@Transactional(readOnly=true)
	public List<User> getUserByUserName(String userName){
		// TODO Auto-generated method stub
		return userDao.getUserByUserName(userName);
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> listUser(int offset,int pageSize) {
		
		return userDao.listUser(offset,pageSize);
	}

	@Override
	@Transactional(readOnly=true)
	public int countUser() {
	
		return userDao.countUser();
	}

	
}
