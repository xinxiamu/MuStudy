<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. 电脑浏览器访问<br>
    
    <%=basePath%>
    
    <p>
    ${aa}
     <p>
    ${sitePreference}
    -----------${kk}
    <p>map---
    ${cc}---${cc.dd}------${cc.bb}--${cc.kk}
    <p>
    list---${list}--${list[1]}
    
    <p>
    ${aadd}
    <p>
    ${basePath}
    
    <br>
    
    <img alt="" src="resources/img/web/散标列表_20141106_640.jpg">
    
    <img alt="" src="resources/img/web/loding_progress.gif">
  </body>
</html>
