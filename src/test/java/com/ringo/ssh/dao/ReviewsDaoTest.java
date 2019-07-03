package com.ringo.ssh.dao;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.Reviews;
import com.ringo.ssh.entity.User;

public class ReviewsDaoTest extends BaseTestCaseJunit44{

	
	@Resource(name="ReviewsDao")
	private IReviewsDao reviewsDao;	
	
	@Test
	@Rollback(false)
	public void testSave() {
		Reviews r=new Reviews();
		Goods g=new Goods();
		g.setGoodsId(2);
		r.setGoods(g);
		User u=new User();
		u.setUserId(1);
		u.setEmail("13922924658@163.com");
		u.setActivationCode("efgh");
		r.setReviewsDate(new Date());
		r.setReviewsInfo("这个衣服还行！！！");
		reviewsDao.save(r);
	}
	
	@Test
	@Rollback(false)
	public void testDelete() {
		reviewsDao.delete(2);
	}
}
