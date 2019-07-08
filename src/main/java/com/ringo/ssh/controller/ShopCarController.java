package com.ringo.ssh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ringo.ssh.entity.ShopCarList_Goods;
import com.ringo.ssh.service.IShopCarService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/shopCar")
public class ShopCarController {
	
	@Resource(name = "ShopCarService")
	private IShopCarService ShopCarService;
	
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
	 * 获得用户所有购物车列表
	 * 
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/getAllShopCarList", method = RequestMethod.GET)
	public void getAllShopCarList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userId");
		if (obj != null) {
			int userId =(int)obj;
			List<ShopCarList_Goods> sclgs=ShopCarService.getShopCarListByUserId(userId);
			JSONArray jsonArray2 = JSONArray.fromObject(sclgs);// 将集合转换为json格式
			renderData(response, jsonArray2.toString());
		}else
			renderData(response, "请先登陆后查看");
		
	}
}
