<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>测试文件上传</title>
	<% String path = request.getContextPath();%>
	<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
	<script type="text/javascript" src="http://localhost:8084/ssh/static/jquery.min.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#submit").click(function(){
		    var fileName=$("#pictureUpload").val();  //获取文件名 
		    var suffixIndex=fileName.lastIndexOf(".");
		    suffix=fileName.substring(suffixIndex+1).toUpperCase();
		    if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){
		    	  var file = $("#pictureUpload");
		    	  file.after(file.clone().val(""));
		    	  file.remove();
		    	  return;
		     }
		 var formData = new FormData();
		 formData.append('file', $('#pictureUpload')[0].files[0]);  //添加图片信息的参数
		    //开始上传
		    $.ajax({
		    	  type: "POST",
		    	        url: "imageUpload",
		    	        data:formData,
		    	        cache: false, //上传文件不需要缓存
		    	        processData: false,// 告诉jQuery不要去处理发送的数据
		    	        contentType: false,// 告诉jQuery不要去设置Content-Type请求头
		    	        encType:"multipart/form-data",
		    	        success: function(data) {
			    	        
		    	           //在这我们可以获取到后台来的路径进行回显
		    	           $("#userHead").attr("src",$("#userHead").attr("src")+"upload/"+data);
		    	        }
		    	    });
		});
	});
	
	</script>
</head>
<body>
	<input type="file"   id="pictureUpload">
	<input type="button" value="提交" id="submit">
	<img src="<%=basePath%>"  alt="用户头像" id="userHead"/>
	
	
</body>
</html>