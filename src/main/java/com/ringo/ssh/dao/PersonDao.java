package com.ringo.ssh.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ringo.ssh.entity.Person;

////说明这是Dao层的bean,并在Ioc容器中创建这个bean
//@Repository
//public class PersonDao {
//	
//	//说明自动Ioc容器中寻找对应的bean注入，和@Autowired类似
//	@Resource 
//	private SessionFactory sessionFactory;
//	
//	//抑制没被使用过的代码的警告
//	@SuppressWarnings("unused")
//	private Session getSession() {
//		return sessionFactory.getCurrentSession();
//	}
//	//抑制没有进行类型检查操作的警告
//	@SuppressWarnings("unchecked")
//	public List<Person> getPerson(){
//		return (List<Person>)this.getSession().createCriteria(Person.class).list();
//	}
//	
//	public Person getPersonById(String id) {
//		return (Person)this.getSession().createQuery("from Person where id =?")
//				.setParameter(0, id).uniqueResult();
//	}
//	public void addPerson(Person person) {
//		this.getSession().save(person);
//	}
//
//	public void updatePerson(Person person) {
//		this.getSession().update(person);
//	}
//	
//	public void deletePerson(String id) {
//		this.getSession().createQuery("delete Person where id =?")
//		.setParameter(0, id).executeUpdate();
//	}
//}

@Repository("PersonDao")
public class PersonDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 保存对象
	 * 
	 * @param p
	 * @return
	 */
	public void save(Person p) {
		sessionFactory.getCurrentSession().save(p);
	}
	/**
	 * 根据UserName来查找用户
	 * 
	 * @param String userName
	 * @return Person
	 */
	public Person getPersonByUserName(String userName) {
		return (Person)sessionFactory.getCurrentSession().createQuery("from Person where userName =?")
				.setParameter(0, userName).uniqueResult();
	}
}
