<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>房屋租赁管理系统-中介</title>
		<link href="<%=basePath%>admin/style.css" type="text/css"
			rel="stylesheet">
	</head>

	<body>
		<div id="container">
			<div id="banner">
				<h1>
					房屋租赁管理系统-中介
				</h1>
			</div>
			<div id="menu">
				<a href="mailto:"><img
						src="<%=basePath%>admin/images/c3.gif" border=0 alt="救助中心"></a>
				 <a href="<%=basePath%>admin/about.jsp"><img src="<%=basePath%>admin/images/c2.gif" border=0 alt="系统简介"></a>
				<img src="<%=basePath%>admin/images/c1.gif" border=0 alt="操作手册">
			</div>
			<br />
			<div id="main">
				<table border="0" align="center">
					<tr>
						<td>
						<img src="<%=basePath%>admin/images/coffe.jpg" border=0 width=720 height=530>
						</td>
					</tr>
				</table>
			</div>
		</div>
<br><br>
<center>
	Copyright &copy; 2013 福建师范大学协和学院信息技术系 地址：福建福州市闽侯上街大学城学府南路 邮编：350108
	联系电话：0591-22868626
</center>
	</body>
</html>