package com.ringo.ssh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ringo.ssh.exception.MyException;
import com.ringo.ssh.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserContorller {
	
	@Resource(name="UserService")
	private IUserService userService;
	
	/**
	   * ajax请求不需要返回页面，只需要得到response中的数据即可，所以方法签名为void即可
	   * 
	   * @param request
	   * @param response 
	   */
	@RequestMapping(value="/userSignIn",method = RequestMethod.POST)
	@ResponseBody//@ResponseBody的作用其实是将java对象转为json格式的数据
	public void personSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {
			if(userService.checkSignIn(email, password)==1) {
				renderData(response,"登陆成功");
			}
		} catch (MyException e) {
			// 如果有异常说明是账户不存在或者密码错误，就向前端发送错误信息
			renderData(response,e.getMessage());
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
	   * 用于跳转到PersonLogin.jsp页面
	   * 
	   * @param 
	   * @param 
	   */
	  @RequestMapping(value="/userSignInPage")
	  public String personSignInPage() {
		  
		  return "userLogin";
	  }
}
