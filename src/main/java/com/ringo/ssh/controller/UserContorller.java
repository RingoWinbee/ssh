package com.ringo.ssh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ringo.ssh.entity.User;
import com.ringo.ssh.exception.MyException;
import com.ringo.ssh.service.IUserService;
import com.ringo.util.CreatRandCode;
import com.ringo.util.FileUpload;
import com.ringo.util.SendMail;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserContorller {

	@Resource(name = "UserService")
	private IUserService userService;

	/**
	 * 用于用户登陆
	 * ajax请求不需要返回页面，只需要得到response中的数据即可，所以方法签名为void即可 检测用户登陆情况
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/userSignIn", method = RequestMethod.POST)
	// @ResponseBody的作用其实是将java对象转为json格式的数据
	public void userSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			int userId = userService.checkSignIn(email, password);
			renderData(response, "登陆成功");
			request.getSession().setAttribute("userId", userId);
		} catch (MyException e) {
			// 如果有异常说明是账户不存在或者密码错误，就向前端发送错误信息
			renderData(response, e.getMessage());
		}
	}

	/**
	 * 用于用户注销登陆
	 * @param request
	 * @param response
	 * @return index.jsp
	 */
	@RequestMapping(value = "/userSignOut", method = RequestMethod.POST)
	public String userSignOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("userId");
		return "index";
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
	 * 用于跳转到userLogin.jsp页面
	 * 
	 * @param
	 * @param
	 */
	@RequestMapping(value = "/userSignInPage",method = RequestMethod.GET)
	public String personSignInPage() {

		return "userLogin";
	}

	/**
	 * 
	 * 用于用户注册
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public void userRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String activationCode = request.getParameter("activationCode");
		if (!userService.isHasUser(email)) {
			HttpSession session = request.getSession(false);
			Object item = session.getAttribute("activationCode");
			String code = item.toString();
			if (!code.equals(activationCode))
				renderData(response, "验证码不匹配！");
			else {
				User u = new User();
				u.setEmail(email);
				u.setPassword(password);
				u.setUserName(userName);
				u.setActivationCode(activationCode);
				userService.saveUser(u);
				renderData(response, "注册成功！");
			}

		} else
			renderData(response, "该邮箱已已被注册！");
	}

	/**
	 * 
	 * 用于向用户邮箱发送6位验证码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/sendUserEmailCode", method = RequestMethod.POST)
	public void sendUserEmailCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		// 构造6位随机验证码
		String activationCode = new CreatRandCode().creatCode();
		System.out.println("验证码：" + activationCode);
		// 将该六位验证码发送到用户邮箱
		try {
			new SendMail(email, activationCode).toSendMail();
			renderData(response, "邮箱验证码已发送");
			HttpSession session = request.getSession();
			session.setAttribute("activationCode", activationCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			renderData(response, e.getMessage());
		}
	}

	/**
	 * 
	 * 用于给用户上传图片
	 * 
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse response
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public void imageUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
		MultipartFile file = mreq.getFile("file");
		String frontName = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/upload/";
		System.out.println(frontName);
		FileUpload f = new FileUpload();
		String fileName = f.imageUpload(file, frontName);
		renderData(response, fileName);
	}

	/**
	 * 用于跳转到上传图片页面.jsp页面
	 * 
	 * @param
	 * @param
	 */
	@RequestMapping(value = "/userUploadPage",method = RequestMethod.GET)
	public String userSignInPage(HttpServletRequest request, HttpServletResponse response) {

		return "imageUpload";
	}

	/**
	 * 
	 * 用于显示用户的基本个人信息
	 * 
	 * @param HttpServletRequest  request
	 * @param HttpServletResponse response
	 * @throws IOException
	 * 
	 * 
	 */

	@RequestMapping(value = "/showUserMasssage", method = RequestMethod.GET)
	public void showUserMasssage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userId");
		if (obj != null) {
			int userId = (int) obj;
			System.out.println("拿到的session:" + userId);
			User u1 = userService.getUserByUserId(userId);
			User u2 = new User();
			u2.setUserName(u1.getUserName());
			u2.setAddress(u1.getAddress());
			u2.setHeadPhoto(u1.getHeadPhoto());
			u2.setPhone(u1.getPhone());
			u2.setRealName(u1.getRealName());
			JSONObject object = JSONObject.fromObject(u2);
			renderData(response, object.toString());
		} else
			renderData(response, "请先登陆后查看");
	}

	/**
	 * 用于跳转到显示用户信息的页面
	 * 
	 * @param
	 * @param
	 */
	@RequestMapping(value = "/showUserMassagePage",method = RequestMethod.GET)
	public String showUserMassagePage(HttpServletRequest request, HttpServletResponse response) {

		return "showMassage";
	}

	/**
	 * 
	 * 用于给用户修改基本个人信息
	 * 
	 * @param HttpServletRequest  request
	 * @param HttpServletResponse response
	 * @throws IOException
	 * 
	 * 
	 */

	@RequestMapping(value="/updateUserMessage",method = RequestMethod.POST)
	public void updateUserMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User u = new User();
		u.setUserId((int) request.getSession().getAttribute("userId"));
		u.setAddress((String) request.getParameter("address"));
		u.setPhone((String) request.getParameter("phone"));
		u.setRealName((String) request.getParameter("realName"));
		u.setUserName((String) request.getParameter("userName"));
		u.setHeadPhoto((String) request.getParameter("headPhoto"));
		userService.updateUser(u);
		renderData(response, "修改成功");
	}
}
