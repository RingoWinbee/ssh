<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>欢迎页面-L-admin1.0</title>
<%
	String path = request.getContextPath();
%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="<%=basePath%>static/favicon.ico"
	type="image/x-icon" />
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="stylesheet" href="<%=basePath%>static/css/font.css">
<link rel="stylesheet" href="<%=basePath%>static/css/xadmin.css">
<script src="<%=basePath%>static/js/jquery.min.js"></script>
<script src="<%=basePath%>static/js/getUrlParam.js"></script>
<script type="text/javascript"
	src="<%=basePath%>static/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>static/js/xadmin.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-body">
		<form class="layui-form">
			<div class="layui-form-item">
				<label for="L_email" class="layui-form-label"> <span
					class="x-red">*</span>商品图片
				</label>
				<div class="layui-input-inline">
					<img alt="商品图片" src="<%=basePath%>" id="headPhoto"
						style="width: 100px; height: 100px;"> <img
						onerror="this.style.display='none'" src="<%=basePath%>"
						id="basePath"> <input type="file" id="pictureUpload">
					<input type="button" value="上传图片" id="upLoadImage">
					<p hidden id="imageName"></p>
					<p hidden id="lastImagePath"></p>
				</div>
				<label for="L_email" class="layui-form-label"> <span
					class="x-red">*</span>商品名
				</label>
				<div class="layui-input-inline">
					<input type="text" id="goodsName" name="email" required
						lay-verify="email" autocomplete="off" class="layui-input">
				</div>
				<label for="L_email" class="layui-form-label"> <span
					class="x-red">*</span>商品描述
				</label>
				<div class="layui-input-inline">
					<input type="text" id="goodsInfo" name="email" required
						lay-verify="email" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_username" class="layui-form-label"> <span
					class="x-red">*</span>价格
				</label>
				<div class="layui-input-inline">
					<input type="text" id="goodsPrice" name="username" required
						lay-verify="nikename" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_pass" class="layui-form-label"> <span
					class="x-red">*</span>真实价格
				</label>
				<div class="layui-input-inline">
					<input type="text" id="goodsRealPrice" name="pass" required
						lay-verify="pass" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> <span
					class="x-red" id="categoryName">*</span>商品类别:
				</label>
				<div class="layui-input-inline">
					修改为：<select>

					</select>
				</div>
			</div>
			
				<button class="layui-btn" lay-filter="save" lay-submit="" id="save">
					保存</button>
			</div>
		</form>
	</div>
	<script>
		var goodsId = getUrlParam("goodsId");
		$.ajax({
			url : "http://localhost:9080/ssh/goods/showGoodsDetial",
			type : "GET",
			data : {
				"goodsId" : goodsId,
			},
			//后台返回的dataType类型和前台写的不一致会跳入error		
			dataType : "json",
			async : false,
			success : function(data) {
				$
				.each(
						data,
						function(i, item) {
				$("#headPhoto")
						.attr(
								"src",
								$("#headPhoto").attr("src") + "upload/"
										+ item.goodsImg);
				console.log($("#headPhoto").attr("src"));
				$("#lastImagePath").val(item.goodsImg);
				$("#imageName").val(item.goodsImg);
				$("#goodsName").val(item.goodsName);
				$("#goodsInfo").val(item.goodsInfo);
				$("#goodsPrice").val(item.goodsPrice);
				$("#goodsRealPrice").val(item.goodsRealPrice);
				$("#categoryName").val(item.category.categoryName);
						});
			},
			//如果有错误抛出则向页面显示错误信息
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
		});
		$.ajax({
			url : "http://localhost:9080/ssh/goods/getAllCategory",
			type : "GET",
			//后台返回的dataType类型和前台写的不一致会跳入error		
			dataType : "json",
			async : false,
			success : function(data) {
				$.each(data, function(i, item) {
					$("select").append(
							"<option value ="+item.categoryId+">"
									+ item.categoryName + "</option>");
				});
			},
			//如果有错误抛出则向页面显示错误信息
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
		});
		layui.use([ 'form', 'layer' ], function() {
			$ = layui.jquery;
			var form = layui.form, layer = layui.layer;

		});
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
						url : "http://localhost:9080/ssh/user/imageUpload",
						data : formData,
						cache : false, //上传文件不需要缓存
						processData : false,// 告诉jQuery不要去处理发送的数据
						contentType : false,// 告诉jQuery不要去设置Content-Type请求头
						encType : "multipart/form-data",
						success : function(data) {

							//在这我们可以获取到后台来的路径进行回显
							$("#headPhoto").attr("src",
									$("#basePath").attr("src"));
							$("#headPhoto").attr(
									"src",
									$("#headPhoto").attr("src") + "upload/"
											+ data);
							$("#imageName").val(data);
							alert($("#imageName").val());
						},
					});

					//删除原来的图片
					$.ajax({
						url : "http://localhost:9080/ssh/user/deleteImage",
						type : "POST",
						async : false,
						data : {
							"lastFileName" : $("#lastImagePath").val()
						},
						success : function(data) {
						},
						//如果有错误抛出则向页面显示错误信息
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							alert(XMLHttpRequest.status);
							alert(XMLHttpRequest.readyState);
							alert(textStatus);
						},
					});
				});
		//保存修改商品的信息
		$("#save").click(function() {
			$.ajax({
				type : "POST",
				url : "http://localhost:9080/ssh/admin/updateGoods",
				dataType : "text",
				data : {
					"goodsId" : goodsId,
					"goodsName" : $("#goodsName").val(),
					"goodsInfo" : $("#goodsInfo").val(),
					"goodsImg" : $("#imageName").val(),
					"goodsPrice" : $("#goodsPrice").val(),
					"goodsRealPrice" : $("#goodsRealPrice").val(),
					"categoryId" : $("select").val(),
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