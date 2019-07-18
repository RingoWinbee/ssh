<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<%
	String path = request.getContextPath();
%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta charset="UTF-8">
<title>欢迎页面-L-admin1.0</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="../css/font.css">
<link rel="stylesheet" href="../css/xadmin.css">
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript" src="../js/xadmin.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-body layui-anim layui-anim-up">
		<form class="layui-form">
			<div class="layui-form-item">
				<label for="L_email" class="layui-form-label"> <span
					class="x-red">*</span>商品名称
				</label>
				<div class="layui-input-inline">
					<input type="text" id="goodsName" name="email" required
						lay-verify="email" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_username" class="layui-form-label"> <span
					class="x-red">*</span>商品描述
				</label>
				<div class="layui-input-inline">
					<input type="text" id="goodsInfo" name="username" required
						lay-verify="nikename" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_pass" class="layui-form-label"> <span
					class="x-red">*</span>商品价格
				</label>
				<div class="layui-input-inline">
					<input type="password" id="goodsPrice" name="pass" required
						lay-verify="pass" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">不能填负数</div>
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> <span
					class="x-red">*</span>商品真实价格
				</label>
				<div class="layui-input-inline">
					<input type="password" id="goodsRealPrice" name="repass" required
						lay-verify="repass" autocomplete="off" class="layui-input">
				</div>
			</div>
			<input type="file" id="pictureUpload"> <input type="button"
				value="上传图片" id="upLoadImage" class="layui-btn">
			<div class="layui-form-item">
				<img alt="商品图片" src="<%=basePath%>" id="headPhoto"
					style="width: 100px; height: 100px;"> <img
					onerror="this.style.display='none'" src="<%=basePath%>"
					id="basePath"> <label for="L_repass" class="layui-form-label">
				</label>
				<p hidden id="imageName"></p>
				<button class="layui-btn" lay-filter="add" lay-submit="" id="save">
					增加</button>
			</div>
		</form>
	</div>
	<script>
		
		
		//图片上传
		$("#upLoadImage").click(
				function() {
					alert("11");
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
						url : "user/imageUpload",
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
							alert($("#imageName").val());
						},
					});
				});
		
		//保存添加的商品信息
		$("#save").click(function() {
			$.ajax({
				type : "POST",
				url : "admin/addGoods",
				dataType : "text",
				data : {
					"goodsName" : $("#goodsPrice").val(),
					"goodsInfo" : $("#goodsInfo").val(),
					"goodsPrice" : $("#goodsPrice").val(),
					"goodsRealPrice" : $("#goodsRealPrice").val(),
					"goodsImg" : $("#imageName").val(),
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
	</script>
	
</body>

</html>