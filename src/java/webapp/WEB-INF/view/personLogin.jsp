<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>用户登陆界面</title>
<script type="text/javascript" src="http://localhost:8084/ssh/static/jquery.min.js"></script>
 <script type="text/javascript">
      function login() {
        $.ajax({
          url: "http://localhost:8084/ssh/person/personSignIn",
          type: "POST",
          //后台返回的dataType类型和前台写的不一致会跳入error		
          dataType: "text",
          data: {
            "userName": $("#userName").val(),
            "password": $("#password").val()
          },
          async: false,
          success: function(data) {
        	  $("#result").html(data);
          },
          //如果有错误抛出则向页面显示错误信息
          error: function(XMLHttpRequest, textStatus, errorThrown) {
              alert(XMLHttpRequest.status);
              alert(XMLHttpRequest.readyState);
              alert(textStatus);
          },
        });
      }
    </script>
</head>
<body>
		<center>
				<p>
					用户名:<input type="text" id="userName"><br>
					密码   : <input type="password" id="password"><br>
					<span id="result"></span><br>
				</p>
				<input type="button" value="登录" onclick="login()" >
		</center>
		
</body>
</html>