package com.ringo.ssh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ringo.ssh.dao.IShopCarListDao;
import com.ringo.ssh.entity.Order;
import com.ringo.ssh.entity.OrderDetails;
import com.ringo.ssh.entity.Order_Goods;
import com.ringo.ssh.entity.User;
import com.ringo.ssh.service.IOrderDetailsService;
import com.ringo.ssh.service.IOrderService;
import com.ringo.ssh.service.IShopCarListService;
import com.ringo.ssh.service.IShopCarService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Resource(name = "OrderService")
	private IOrderService orderService;
	
	@Resource
	private IOrderDetailsService orderDetailsService;
	
	@Resource(name = "ShopCarListService")
	IShopCarListService shopCarListService;
	
	/**
	 * 通过PrintWriter将响应数据写入response，ajax可以接受到这个数据
	 * 
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	private void renderData(HttpServletResponse response, String data) throws IOException {
		PrintWriter printWriter = null;
		printWriter = response.getWriter();
		printWriter.print(data);
		if (null != printWriter) {
			printWriter.flush();
			printWriter.close();
		}
	}
	
	/**
	 * 获得用户所有订单列表
	 * 
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/getAllOrderList", method = RequestMethod.GET)
	public void getAllOrderList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userId");
		if (obj != null) {
			int userId =(int)obj;
			List<Order_Goods> ogs=orderDetailsService.getOrderDetailsByUserId(userId);
			if(ogs.size()!=0) {
				JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
				jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
				jsonConfig.setExcludes(new String[] { "goods","shopCarList","orderDetails"}); // 此处是亮点，只要将所需忽略字段加到数组中即可
				JSONArray ods = JSONArray.fromObject(ogs,jsonConfig);// 将集合转换为json格式
				renderData(response, ods.toString());
			}else
				renderData(response, "目前暂无订单");
		}else
			renderData(response, "请先登陆后查看");
	}
	
	/**
	 * 将购物车的商品提交到订单
	 * 
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/addGoodsToOrder", method = RequestMethod.POST)
	public void addGoodsToOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int[] shopCarListIds=(int[])request.getSession().getAttribute("shopCarListIds");
		String consigneeName=(String)request.getParameter("consigneeName");
		String orderAddress=(String)request.getParameter("orderAddress");
		String orderPhone=(String)request.getParameter("orderPhone");
		int orderState=Integer.parseInt((String)request.getParameter("orderState"));
		Double totalMoney=Double.valueOf((String)request.getParameter("totalMoney"));
		User u=new User();
		u.setUserId((int)request.getSession().getAttribute("userId"));
		//构造订单对象
		Order o=new Order();
		o.setConsigneeName(consigneeName);
		o.setOrderAddress(orderAddress);
		o.setOrderDate(new Date());
		o.setOrderPhone(orderPhone);
		o.setOrderState(orderState);
		o.setTotalMoney(totalMoney);
		o.setUsers(u);
		//保存订单到数据库
		int orderId=orderService.saveOrder(o);
		//生成订单详情表
		Order order_item=new Order();
		order_item.setOrderId(orderId);
		OrderDetails od=null;
		for(int scId:shopCarListIds) {
			//计算订单的单个商品总金额
			od=new OrderDetails();
			Double price=shopCarListService.getShopCarListyByShopCarListId(scId).getGoods().getGoodsRealPrice();
			int count=shopCarListService.getShopCarListyByShopCarListId(scId).getGoodsCount();
			Double sum=price*count;
			od.setSumMoney(sum);
			od.setGoodsCount(count);
			od.setGoods(shopCarListService.getShopCarListyByShopCarListId(scId).getGoods());
			od.setOrder(order_item);
			orderDetailsService.saveOrderDetails(od);
			//删除对应的购物车列表
			shopCarListService.deleteShopCarList(scId);
			//清除session中的shopCarListIds
			request.getSession().removeAttribute("shopCarListIds");
			renderData(response, "添加成功");
		}
	}
	
	/**
	 * 删除订单
	 * 
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int orderId=Integer.parseInt((String)request.getParameter("orderId"));
		orderService.deleteOrder(orderId);
		renderData(response, "删除成功");
	}
	
	/**
	 * 删除订单状态为已支付
	 * 
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateOrderState", method = RequestMethod.POST)
	public void updateOrderState(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int orderId=Integer.parseInt((String)request.getParameter("orderId"));
		Order o=orderService.getOrderByOrderId(orderId);
		o.setOrderState(1);
		orderService.updateOrder(o);
		renderData(response, "支付成功");
	}
}
