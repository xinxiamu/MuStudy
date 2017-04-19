<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="zh-cn">
<!-- InstanceBegin template="/Templates/temp.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- InstanceBeginEditable name="meta" -->
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="title" -->
<title>${i18n.title} - 404</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="css" -->
<link rel="stylesheet" type="text/css" href="${staticPath}static/css/main.css">
<link rel="stylesheet" type="text/css" href="${staticPath}static/css/404.css">
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="javascript" -->
<script type="text/javascript">
	
</script>

<!-- InstanceEndEditable -->
</head>
<body>

	<!-- 内容页面部分 begin -->
	<div class="main">
		<div class="main-top">
			<img src="${staticPath}static/images/404.png">
		</div>
		<div class="body-bottom">
			<a href="javascript:history.back();" >
				<input class="refresh" type="button" value="${i18n.back}" />
			</a> 
			<a href="${basePath}">
				<input class="back" type="button" value="${i18n.index}" />
			</a>
		</div>
	</div>
</body>
<!-- InstanceEnd -->
</html>
