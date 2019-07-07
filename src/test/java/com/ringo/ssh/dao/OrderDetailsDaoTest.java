package com.ringo.ssh.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.Order;
import com.ringo.ssh.entity.OrderDetails;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class OrderDetailsDaoTest extends BaseTestCaseJunit44{

	@Resource(name="OrderDetailsDao")
	private IOrderDetailsDao OrderDetailsDao;	

	@Test
	@Rollback(false)
	public void testSave() {
		OrderDetails od=new OrderDetails();
		Goods g=new Goods();
		g.setGoodsId(1);
		od.setGoods(g);
		od.setGoodsCount(2);
		Order o=new Order();
		o.setOrderId(1);
		od.setOrder(o);
		od.setSumMoney(50.00);
		OrderDetailsDao.save(od);
	}

	@Test
	@Rollback(false)
	public void testGetOrderDetailsById() {
		OrderDetails od=OrderDetailsDao.getOrderDetailsByOrderDetailsId(1);
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"category","shopCarList","orderDetails","shopCar","users"});  //此处是亮点，只要将所需忽略字段加到数组中即可
		JSONArray jsonArray2 = JSONArray.fromObject(od,jsonConfig);//将集合转换为json格式
		System.out.println(jsonArray2);
		//[{"goods":{"goodsDate":{"date":7,"day":0,"hours":15,"minutes":38,"month":6,"nanos":0,"seconds":41,"time":1562485121000,"timezoneOffset":-480,"year":119},"goodsId":1,"goodsImg":"","goodsInfo":"街舞专业","goodsName":"短袖","goodsPrice":10,"goodsRealPrice":10,"goodsState":0,"goodsStock":0},"goodsCount":2,"order":{"consigneeName":"李卓岚","orderAddress":"广东省东莞市","orderDate":{"date":7,"day":0,"hours":15,"minutes":50,"month":6,"nanos":0,"seconds":19,"time":1562485819000,"timezoneOffset":-480,"year":119},"orderId":1,"orderPhone":"18825510040","orderState":0,"totalMoney":50},"orderDetailsListId":1,"sumMoney":50}]
		/**查询到的信息
		 * goods(该订单详情表的信息,但没有商品类别信息)
		 * orderDetials(订单详情表的信息)
		 * order(该订单详情表所属的订单信息,没有用户信息)
		 */
	}
}
