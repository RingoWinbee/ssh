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

import com.ringo.ssh.entity.Goods;
import com.ringo.ssh.entity.ShopCar;
import com.ringo.ssh.entity.ShopCarList;
import com.ringo.ssh.entity.ShopCarList_Goods;
import com.ringo.ssh.service.IShopCarListService;
import com.ringo.ssh.service.IShopCarService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/shopCar")
public class ShopCarController {
	
	@Resource(name = "ShopCarService")
	private IShopCarService shopCarService;
	
	@Resource(name = "ShopCarListService")
	private IShopCarListService shopCarListService;
	
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
			List<ShopCarList_Goods> sclgs=shopCarService.getShopCarListByUserId(userId);
			JSONArray jsonArray2 = JSONArray.fromObject(sclgs);// 将集合转换为json格式
			renderData(response, jsonArray2.toString());
		}else
			renderData(response, "请先登陆后查看");
		
	}
	
	/**
	 * 添加用户购物车列表
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/addShopCarList", method = RequestMethod.POST)
	public void addShopCarList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userId");
		//判断是否已经登陆
		if (obj != null) {
			int userId =(int)obj;
			ShopCar sc=shopCarService.getShopCarByUserId(userId);
			int goodsId=Integer.parseInt((String)request.getParameter("goodsId"));
			ShopCarList scl=shopCarListService.getShopCarListyByGoodsId(goodsId);
			//如果购物车列表中不包含此商品的话，就新建一个购物车列表保存
			if(scl==null) {
				Goods g=new Goods();
				g.setGoodsId(goodsId);
				ShopCarList shopCarList =new ShopCarList();
				shopCarList.setGoods(g);
				shopCarList.setGoodsCount(1);
				shopCarList.setShopCar(sc);
				shopCarListService.saveShopCarList(shopCarList);
			//如果购物车列表中包含此商品的话，就直接数量加1即可
			}else {
				scl.setGoodsCount(scl.getGoodsCount()+1);
				shopCarListService.updateShopCarList(scl);
			}
			renderData(response, "添加成功");
		}else
			renderData(response, "请先登陆后操作");
		
	}
	
	/**
	 * 修改用户购物车列表的商品数量
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateGoodsCount", method = RequestMethod.POST)
	public void updateGoodsCount(HttpServletRequest request, HttpServletResponse response) throws IOException{
			int sclId=Integer.parseInt((String)request.getParameter("shopListCarListId"));
			int newCount=Integer.parseInt((String)request.getParameter("newCount"));
			ShopCarList scl=shopCarListService.getShopCarListyByShopCarListId(sclId);
			scl.setGoodsCount(newCount);
			shopCarListService.updateShopCarList(scl);
			renderData(response, "修改成功");
	}
	
	/**
	 * 删除用户购物车列表
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteShopCarList", method = RequestMethod.POST)
	public void deleteShopCarList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int sclId=Integer.parseInt((String)request.getParameter("shopListCarListId"));
		System.out.println("sclId:"+sclId);
		shopCarListService.deleteShopCarList(sclId);
		renderData(response, "删除成功");
	}
}
