package com.ringo.ssh.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cache.ehcache.internal.util.HibernateUtil;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.User;


public class UserDaoTest extends BaseTestCaseJunit44 {

	//@Autowired
	@Resource(name="UserDao")
	private IUserDao userDao;	
	
	@Test
	@Rollback(false)
	public void testSave() {
		User u=new User();
		u.setUserName("Ringo");
		u.setEmail("13922924658@163.com");
		u.setPassword("104");
		u.setActivationCode("efgh");
		userDao.save(u);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		User u=new User();
		u.setUserId(1);
		u.setUserName("Ring");
		u.setEmail("13922924658@163.com");
		u.setPassword("1041233");
		u.setActivationCode("abcd123");
		userDao.update(u);
	}
	
	@Test
	@Rollback(false)
	public void testDelete() {
		userDao.delete(3);
	}
	
	@Test
	public void testGetPersonByEmail() {
		User u=userDao.getPersonByEmail("13922924658@qq.com");
		if(u==null)
			System.out.println("找不到此用户");
		else
			System.out.println("OK");
	}
	
//	@Test
//	@Rollback(false)
//	public void testQueryUser() {
//		Session s=null;
//	       try{
//	           s=HibernateUtil.getSession();
//	           //hibernate的查询语言.from后面+类名（区分大小写）.也可以用as给类起一个别名，as可以省略
//	           //按照姓名查找
//	           String queryString="from User as user order by user.userId"; 
//	           Query query=s.createQuery(queryString);
//	           //query.setString("n",name);
//	           //分页显示的操作
//	           query.setFirstResult(1);//显示第几页，当前页
//	           query.setMaxResults(2);//每页做多显示的记录数
//	           //返回多行结果集
//	           List<User> list=query.list();
//	           for(User u:list){
//	              System.out.println(u.getUserId()+":"+u.getUserName());
//	           }	      
//	           /*User user=(User) query.uniqueResult();
//
//	           System.out.println(user.getId()+":"+user.getName());*/
//	         
//	           /*query.uniqueResult();//返回一行结果集*/      
//	       }finally{
//	           if(s!=null){
//	              s.close();
//	           }
//	       }
//	}
//	
}
