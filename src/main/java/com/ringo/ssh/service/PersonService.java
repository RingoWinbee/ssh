package com.ringo.ssh.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ringo.ssh.dao.PersonDao;
import com.ringo.ssh.entity.Person;
import com.ringo.ssh.exception.MyException;

//@Service
//public class PersonService {
//	@Autowired
//	private PersonDao personDao;
//	public List<Person> getPerson(){
//		return null;
//	}
//	public void getPersonById(String id) {
//		personDao.getPersonById(id);
//	}
//	public void addPerson(Person person) {
//		personDao.addPerson(person);
//	}
//
//	public void updatePerson(Person person) {
//		personDao.updatePerson(person);
//	}
//	
//	public void deletePerson(String id) {
//		personDao.deletePerson(id);
//	}
//}

@Service("personService")
public class PersonService {

	@Resource(name = "PersonDao")
	private PersonDao personDao;

	@Transactional
	public void save(Person p) {
		personDao.save(p);
	}

	@Transactional
	public int checkSignIn(String userName,String password) throws MyException {
		Person p1=personDao.getPersonByUserName(userName);
		if(p1==null)
			throw new MyException("该用户名不存在！");// new一个自己的异常类
		else if(!password.equals(p1.getPassword()))
			throw new MyException("密码错误！");// new一个自己的异常类
		else 
			return 1;
	}
}
