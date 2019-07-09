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

import com.ringo.ssh.entity.User;
import com.ringo.ssh.exception.MyException;
import com.ringo.ssh.service.IAdminService;
import com.ringo.ssh.service.IGoodsService;
import com.ringo.ssh.service.IOrderService;
import com.ringo.ssh.service.IUserService;
import com.ringo.util.CreatRandCode;

import net.sf.json.JSONObject;

//@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource(name = "AdminService")
	private IAdminService adminService;
	
	@Resource(name = "UserService")
	private IUserService userService;
	
	@Resource(name = "GoodsService")
	private IGoodsService goodsService;
	
	@Resource(name = "OrderService")
	private IOrderService orderService;
	
	
	/**
	 * 主页
	 * @return
	 */
	@RequestMapping(value = "/adminIndex",method = RequestMethod.GET)
	public String indexPage() {

		return "admin/index";
	}
	
	/**
	 * 管理员登陆后台
	 */
	@RequestMapping(value = "/adminSignIn", method = RequestMethod.POST)
	// @ResponseBody的作用其实是将java对象转为json格式的数据
	public void adminSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			int adminId = adminService.checkSignIn(email, password);
			renderData(response, "登陆成功");
			request.getSession().setAttribute("adminId", adminId);
		} catch (MyException e) {
			// 如果有异常说明是账户不存在或者密码错误，就向前端发送错误信息
			renderData(response, e.getMessage());
		}
	}

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
	 * 后台管理员展示用户
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录条数
	 */
	@RequestMapping(value = "/listUser", method = RequestMethod.POST)
	private void listUser(HttpServletRequest request, HttpServletResponse response) {
		int offset = Integer.parseInt(request.getParameter("offset"));
		int pageSize = 10;
		
		//获得总数
		int userCount = userService.countUser();
		int pageCount = (userCount/pageSize) + 1;
		 System.out.println(userCount);
		 
		 //获得分页的用户对象
		List<User> userList = userService.listUser(offset,pageSize);
		 for(User u:userList){
             System.out.println(u.getUserId()+":"+u.getEmail());
          }
		 //封装数据返回
		 
	}
	
	/**
	 * 后台管理员添加用户
	 * @throws IOException 
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String activationCode = new CreatRandCode().creatCode();
		if (!userService.isHasUser(email)) {
			User u = new User();
			u.setEmail(email);
			u.setPassword(password);
			u.setUserName(userName);
			u.setActivationCode(activationCode);
			userService.saveUser(u);
			renderData(response, "添加成功！");
		} else {
			renderData(response, "该邮箱已存在！");
		}
	}
	
	
	/**
	 * 后台管理员删除用户
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int userId = Integer.parseInt(request.getParameter("userId"));
		userService.deleteUser(userId);
		renderData(response, "删除成功！");
	}
	
	/**
	 * 后台管理员模糊查找用户（userName）
	 * @throws IOException 
	 */
	@RequestMapping(value = "/findUserByUserName", method = RequestMethod.POST)
	private void findUserByUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userName = request.getParameter("userName");
		List<User> userList = userService.getUserByUserName(userName);
		if(userList != null) {
			renderData(response, "查询成功！");
			//测试打印
			System.out.println(userList);
		}else {
			renderData(response, "没有该用户！");
		}
	}
	
	/**
	 * 后台管理员获取用户信息
	 */
	@RequestMapping(value = "/getUserDetail", method = RequestMethod.POST)
	private void getUserDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User u1 = userService.getUserByUserId(Integer.parseInt(request.getParameter("userId")));
//		u.setAddress((String) request.getParameter("address"));
//		u.setPhone((String) request.getParameter("phone"));
//		u.setRealName((String) request.getParameter("realName"));
//		u.setUserName((String) request.getParameter("userName"));
//		u.setHeadPhoto((String) request.getParameter("headPhoto"));
//		u.setHeadPhoto((String) request.getParameter("password"));
//		userService.updateUser(u);
//		renderData(response, "修改成功");
		
		//响应前台
		User u2 = new User();
		u2.setUserName(u1.getUserName());
		u2.setAddress(u1.getAddress());
		u2.setHeadPhoto(u1.getHeadPhoto());
		u2.setPhone(u1.getPhone());
		u2.setRealName(u1.getRealName());
		JSONObject object = JSONObject.fromObject(u2);
		renderData(response, object.toString());
	}
	
	/**
	 * 后台管理员展示商品
	 */
	@RequestMapping(value = "/listGoods", method = RequestMethod.POST)
	private void listGoods(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	/**
	 * 后台管理员添加商品
	 */
	@RequestMapping(value = "/addGoods", method = RequestMethod.POST)
	private void addGoods(HttpServletRequest request, HttpServletResponse response) {
		String goodsName=(String)request.getParameter("goodName");
	}
	
	/**
	 * 后台管理员删除商品
	 */
	@RequestMapping(value = "/deleteGoods", method = RequestMethod.POST)
	private void deleteGoods(HttpServletRequest request, HttpServletResponse response) {
	
	}
	
	/**
	 * 后台管理员模糊查询商品
	 */
	@RequestMapping(value = "/searchGoods", method = RequestMethod.POST)
	private void searchGoods(HttpServletRequest request, HttpServletResponse response) {
	
	}
	
	/**
	 * 后台管理员获取商品信息
	 */
	@RequestMapping(value = "/getGoodsDetail", method = RequestMethod.POST)
	private void getGoodsDetail(HttpServletRequest request, HttpServletResponse response) {
	
	}
	
	/**
	 * 后台管理员展示订单
	 */
	@RequestMapping(value = "/listOrder", method = RequestMethod.POST)
	private void listOrder(HttpServletRequest request, HttpServletResponse response) {
	
	}
	/**
	 * 后台管理员添加订单
	 */
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	private void addOrder(HttpServletRequest request, HttpServletResponse response) {
	
	}
	
	/**
	 * 
	 * 后台模糊查询订单
	 */
	@RequestMapping(value = "/searchOrder", method = RequestMethod.POST)
	private void searchOrder(HttpServletRequest request, HttpServletResponse response) {
	
	}
	
	/**
	 * 后台获取订单信息
	 */
	@RequestMapping(value = "/getOrderDetail", method = RequestMethod.POST)
	private void getOrderDetail(HttpServletRequest request, HttpServletResponse response) {
	
	}
	
	/**
	 * 后台添加展示评价
	 */
	@RequestMapping(value = "/addReviews", method = RequestMethod.POST)
	private void addReviews(HttpServletRequest request, HttpServletResponse response) {
	
	}
	
	/**
	 * 后台删除评价
	 */
	@RequestMapping(value = "/deleteReviews", method = RequestMethod.POST)
	private void deleteReviews(HttpServletRequest request, HttpServletResponse response) {
	
	}
	
	/**
	 * 后台模糊查询评价
	 */
	@RequestMapping(value = "/searchReviews", method = RequestMethod.POST)
	private void searchReviews(HttpServletRequest request, HttpServletResponse response) {
	
	}
	
	/**
	 * 后台展示分类
	 */
	@RequestMapping(value = "/listCategory", method = RequestMethod.POST)
	private void listCategory(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	/**
	 * 后台添加分类
	 */
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	private void addCategory(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	/**
	 * 后台删除分类
	 */
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	/**
	 * 后台获取分类信息
	 */
	@RequestMapping(value = "/getCategoryDetail", method = RequestMethod.POST)
	private void getCategoryDetail(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	/**
	 * 后台模糊查询分类
	 */
	@RequestMapping(value = "/searchCategory", method = RequestMethod.POST)
	private void searchCategory(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
