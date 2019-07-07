package com.ringo.ssh.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.Order;
import com.ringo.ssh.entity.ShopCar;
import com.ringo.ssh.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class OrderDaoTest extends BaseTestCaseJunit44{

	
	@Resource(name="OrderDao")
	private IOrdersDao OrderDao;	
	
	@Test
	@Rollback(false)
	public void testSave() {
		Order o=new Order();
		o.setConsigneeName("李卓岚");
		o.setOrderAddress("广东省东莞市");
		o.setOrderDate(new Date());
		o.setOrderPhone("18825510040");
		o.setOrderState(0);
		o.setTotalMoney(50.00);
		User u=new User();
		u.setUserId(2);
		o.setUsers(u);
		OrderDao.save(o);
	}

	@Test
	@Rollback(false)
	public void testGetByUserId() {
	Order sc=OrderDao.getOrderByOrderId(1);
	JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
	jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
	jsonConfig.setExcludes(new String[]{"shopCar","goods","order","users"});  //此处是亮点，只要将所需忽略字段加到数组中即可
	JSONArray jsonArray2 = JSONArray.fromObject(sc,jsonConfig);//将集合转换为json格式
	System.out.println(jsonArray2);
	//[{"consigneeName":"李卓岚","orderAddress":"广东省东莞市","orderDate":{"date":7,"day":0,"hours":15,"minutes":50,"month":6,"nanos":0,"seconds":19,"time":1562485819000,"timezoneOffset":-480,"year":119},"orderDetails":[{"goodsCount":2,"orderDetailsListId":1,"sumMoney":50}],"orderId":1,"orderPhone":"18825510040","orderState":0,"totalMoney":50}]
	/**查到的信息
	 * order(订单本身的信息)
	 * orderDetails(属于该订单的订单详情信息,没有商品信息,通过orderDetailsListId来查询)
	 */
	}
}
