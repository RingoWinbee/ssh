package com.ringo.ssh.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.OrderDetails;
import com.ringo.ssh.entity.Reviews;
import com.ringo.ssh.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class ReviewsDaoTest extends BaseTestCaseJunit44{

	
	@Resource(name="ReviewsDao")
	private IReviewsDao reviewsDao;	
	
	@Test
	@Rollback(false)
	public void testSave() {
		Reviews r=new Reviews();
		Goods g=new Goods();
		g.setGoodsId(2);
		OrderDetails od=new OrderDetails();
		od.setOrderDetailsId(4);
		r.setOrderdetails(od);
		r.setReviewsDate(new Date());
		r.setReviewsInfo("这个衣服还行！！！");
		reviewsDao.save(r);
	}
	
	@Test
	@Rollback(false)
	public void testDelete() {
		reviewsDao.delete(2);
	}
	
	@Test
	@Rollback(false)
	public void testGetAllReviews() {
		List<Reviews> rs=reviewsDao.getAllReviews();
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"goods","reviews","order"});  //此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(rs,jsonConfig);//将集合转换为json格式
		System.out.println(jsonArray2);
		//[{"orderdetails":{"goodsCount":2,"orderDetailsId":4,"sumMoney":50},"reviewsDate":{"date":7,"day":0,"hours":18,"minutes":43,"month":6,"nanos":0,"seconds":43,"time":1562496223000,"timezoneOffset":-480,"year":119},"reviewsId":3,"reviewsInfo":"这个衣服还行！！！"}]
		/**查询的信息
		 * reviews(评论本身的信息)
		 * orderdetails(该评论对应的订单详情信息,没有商品信息)
		 */
	}
}
