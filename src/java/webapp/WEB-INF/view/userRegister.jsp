<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>用户登陆界面</title>
<%
	String path = request.getContextPath();
%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="<%=basePath%>static/jquery.min.js"></script>
<link href = "<%=basePath%>static/bootstrap.min.css" rel = "stylesheet">
<script type="text/javascript">
	function login() {
		$.ajax({
			url : "http://localhost:8080/ssh/user/userRegister",
			type : "POST",
			//后台返回的dataType类型和前台写的不一致会跳入error		
			dataType : "text",
			data : {
				"email" : $("#email").val(),
				"password" : $("#password").val(),
				"userName":$("#userName").val(),
				"activationCode":$("#activationCode").val()
			},
			async : false,
			success : function(data) {
				$("#result").html(data);
			},
			//如果有错误抛出则向页面显示错误信息
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
		});
	}
		
		function code() {
			$.ajax({
				url : "http://localhost:8080/ssh/user/sendUserEmailCode",
				type : "POST",
				//后台返回的dataType类型和前台写的不一致会跳入error		
				dataType : "text",
				data : {
					"email" : $("#email").val()
				},
				async : false,
				success : function(data) {
					$("#result").html(data);
				},
				//如果有错误抛出则向页面显示错误信息
				error : function(XMLHttpRequest, textStatus, errorThrown) {
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
			邮箱:<input type="text" id="email"><br> 密码 : <input
				type="password" id="password"><br> 
				用户名 : <input
				type="text" id="userName"><br> 
				验证码: <input
				type="text" id="activationCode"><br>
				<span id="result"></span><br>
		</p>
		<input type="button" value="发送验证码" onclick="code()">
		<input type="button" value="注册" onclick="login()">
	</center>

</body>
</html>