<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>试试session</title>
<script type="text/javascript" src="http://localhost:8084/ssh/static/jquery.min.js"></script>
 <script type="text/javascript">
 $(function(){
     $.ajax({
       url: "http://localhost:8084/ssh/user/showUserMasssage",
       type: "POST",
       //后台返回的dataType类型和前台写的不一致会跳入error		
       //dataType: "json",
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
   });
 </script>
</head>
<body>
		<span id="result"></span>
</body>
</html>