<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>楹木科技有限公司</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="${basePath}resources/bootstrap/3.3/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="${basePath}resources/bootstrap/3.3/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${basePath}resources/js/jquery-1.11.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${basePath}resources/bootstrap/3.3/js/bootstrap.min.js"></script>

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="http://www.baidu.com">楹木科技</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<form class="navbar-form navbar-right" role="form">
					<div class="form-group">
						<input type="text" placeholder="电子邮件/手机号码/用户名"
							class="form-control">
					</div>
					<div class="form-group">
						<input type="password" placeholder="密码" class="form-control">
					</div>
					<button type="submit" class="btn btn-success">登录</button>
					<button type="submit" class="btn btn-danger">注册</button>
				</form>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>

	<div class="jumbotron">
		<div class="container">
			<h1>${slogan}</h1>
			<p>${company_intro}</p>
			<p>
				<a class="btn btn-primary btn-lg" href="${basePath}app-logistics"
					role="button">更多</a>
			</p>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h2>${app_01}</h2>
				<p>
				<h4>${app_01_details}</h4>
				</p>
				<p>
					<a class="btn btn-default" href="${basePath}app-logistics"
						role="button">详情 »</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>${app_02}</h2>
				<p>
				<h4>${app_02_details}</h4>
				</p>
				<p>
					<a class="btn btn-default" href="${basePath}myHomepage"
						role="button">详情 »</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>${app_03}</h2>
				<p>
				<h4>${app_03_details}</h4>
				</p>
				<p>
					<a class="btn btn-default"
						href="http://v3.bootcss.com/examples/jumbotron/#" role="button">详情
						»</a>
				</p>
			</div>
		</div>
	</div>

	<hr>

	<!-- 页脚 -->
	<footer class="footer"
		style="text-align:center;bottom: 10px;top: 10px;position: relative;">
		<div class="row">
			<div class="col-sm-12 text-center">
				版权所有 2014 | 公司：楹木科技 | <a href="${basePath}company-homepage">主页</a>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12 text-center">
				亲情链接： <a href="http://www.0256.cn/">http://www.0256.cn/</a>
				&nbsp;&nbsp;&nbsp <a href="http://zc.0256.cn/index.htm">http://zc.0256.cn/index.htm</a>
			</div>
		</div>
	</footer>
</body>
</html>
