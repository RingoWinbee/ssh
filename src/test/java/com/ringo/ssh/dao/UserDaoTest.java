package com.ringo.ssh.dao;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class UserDaoTest extends BaseTestCaseJunit44{

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
		userDao.delete(1);
	}
	
	@Test
	public void testGetPersonByEmail() {
		User u=userDao.getPersonByEmail("13922924658@163.com");
		
		if(u==null)
			System.out.println("找不到此用户");
		else {
			System.out.println("OK");
		}
			
	}
	
	@Test
	public void testGetPersonById() {
		User u=userDao.getPersonByUserId(2);
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"users","shopCar","order"});  //此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(u,jsonConfig);//将集合转换为json格式
		System.out.println(jsonArray2);
		//[{"activationCode":"efgh","address":"","email":"13922924658@163.com","headPhoto":"","password":"104","phone":"","realName":"","state":0,"userId":1,"userName":"Ringo"}]
		/**查询的信息
		 * user(用户的基本信息)
		 */
	}
}
