<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>试试session</title>
<%
	String path = request.getContextPath();
%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript"
	src="http://localhost:8084/ssh/static/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		//用于向后台获取用户信息
		$.ajax({
			url : "http://localhost:8084/ssh/user/showUserMasssage",
			type : "GET",
			//后台返回的dataType类型和前台写的不一致会跳入error		
			//dataType: "json",
			async : false,
			success : function(data) {
				var obj = JSON.parse(data);
				alert(obj.userName);
				$("#userName").val(obj.userName);
				$("#realName").val(obj.realName);
				$("#phone").val(obj.phone);
				$("#address").val(obj.address);
				$("#headPhoto").val(obj.headPhoto);
			},
			//如果有错误抛出则向页面显示错误信息
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
		});

		//图片上传
		$("#upLoadImage").click(
				function() {
					var fileName = $("#pictureUpload").val(); //获取文件名 
					var suffixIndex = fileName.lastIndexOf(".");
					suffix = fileName.substring(suffixIndex + 1).toUpperCase();
					if (suffix != "BMP" && suffix != "JPG" && suffix != "JPEG"
							&& suffix != "PNG" && suffix != "GIF") {
						var file = $("#pictureUpload");
						file.after(file.clone().val(""));
						file.remove();
						return;
					}
					var formData = new FormData();
					formData.append('file', $('#pictureUpload')[0].files[0]); //添加图片信息的参数
					//开始上传
					$.ajax({
						type : "POST",
						url : "imageUpload",
						data : formData,
						cache : false, //上传文件不需要缓存
						processData : false,// 告诉jQuery不要去处理发送的数据
						contentType : false,// 告诉jQuery不要去设置Content-Type请求头
						encType : "multipart/form-data",
						success : function(data) {

							//在这我们可以获取到后台来的路径进行回显
							$("#headPhoto").attr(
									"src",
									$("#headPhoto").attr("src") + "upload/"
											+ data);
							$("#imageName").val(data);
							alter($("#imageName").val());
						},
					});
				});

		//保存用户修改的信息
		$("#save").click(function() {
			$.ajax({
				type : "POST",
				url : "updateUserMessage",
				dataType : "text",
				data : {
					"userName" : $("#userName").val(),
					"realName" : $("#realName").val(),
					"phone" : $("#phone").val(),
					"address" : $("#address").val(),
					"headPhoto" : $("#imageName").val(),
				},
				async : false,
				success : function(data) {
					alert(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
				},
			});
		});
	});
</script>
</head>
<body>
	<img alt="用户头像" src="<%=basePath%>" id="headPhoto">
	<p hidden id="imageName"></p>
	<br>
	<input type="file" id="pictureUpload">
	<input type="button" value="上传图片" id="upLoadImage">
	<br> 用户名:
	<input type="text" id="userName">
	<br> 真实姓名:
	<input type="text" id="realName">
	<br> 联系方式:
	<input type="text" id="phone">
	<br> 地址：
	<input type="text" id="address">
	<br>
	<input type="button" id="save">
</body>
</html>