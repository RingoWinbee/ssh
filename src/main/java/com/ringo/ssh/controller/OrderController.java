package com.ringo.ssh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ringo.ssh.entity.Order;
import com.ringo.ssh.entity.Order_Goods;
import com.ringo.ssh.service.IOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Resource(name = "OrderService")
	private IOrderService orderService;
	
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
			List<Order> os=orderService.getOrdersByUserId(userId);
			if(os.size()!=0) {
				List<Order_Goods> ogs=new ArrayList<Order_Goods>();
				Order_Goods og=new Order_Goods();
				for(Order o:os) {
					og.setOrderId(o.getOrderId());
				}
			}else
				renderData(response, "暂无订单");
		}else
			renderData(response, "请先登陆后查看");
	}
}
