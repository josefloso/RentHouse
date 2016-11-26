<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.cuc.model.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
    //取管理员对象
    Admin admin = (Admin) session.getAttribute("admin");
%>
<html>
	<head>
		<title>房屋中介管理系统-管理员</title>
		<link href="<%=basePath%>admin/style.css" type="text/css"
			rel="stylesheet">
	</head>
	<body>
		<div id="container">
			<div id="banner">
				<h1>
					房屋中介管理系统-管理员
				</h1>
			</div>
			<div id="menu">
				<a href="mailto:"><img
						src="<%=basePath%>admin/images/c3.gif" border=0 alt="救助中心"></a>
				 <a href="<%=basePath%>admin/about.jsp"><img src="<%=basePath%>admin/images/c2.gif" border=0 alt="系统简介"></a>
				<img src="<%=basePath%>admin/images/c1.gif" border=0 alt="操作手册">
			</div>
			<div id="main">