package com.ringo.ssh.dao;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ringo.ssh.entity.Person;

public class PersonDaoTest extends BaseTestCaseJunit44{

	@Autowired
	private PersonDao personDao;
	@Test
	public void testSave() {
		Person p = new Person();
		p.setUserName("Winbee");
		p.setSex("女");
		p.setPassword("123");
		personDao.save(p);
	}
	
	@Test
	public void testGetPersonByUserName() {
		Person p=personDao.getPersonByUserName("Ring");
		if(p==null)
			System.out.println("没有这个用户");
	}
	@Test
	public void testInit() {
		//启动hibernate时会自动检查数据库，如果缺少表，则自动建表；如果表里缺少列，则自动添加列
	}

}
