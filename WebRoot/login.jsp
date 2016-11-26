<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%
        //取得项目名
	String path = request.getContextPath();
        //合成带有协议、主机名、端口号、项目名的完整URL绝对地址
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>房屋租赁中介管理系统</title>
		<link href="<%=basePath%>style.css" type="text/css"
			rel="stylesheet">
	</head>

	<body>
<SCRIPT LANGUAGE="JavaScript">
	function changeCode() {  
	       var img=document.getElementById("checkCode"); 
	       img.src="<%=basePath%>imgcode.jsp?date="+new Date();//此处很重要，不加后面的就不会变
	}    
</SCRIPT>
		<div id="container">
			<div id="banner">
				<h1>
					房屋租赁中介管理系统
				</h1>
			</div>
			<div id="menu">
				<a href="mailto:"><img
						src="<%=basePath%>images/c3.gif" border=0 alt="救助中心"></a>
				 <a href="<%=basePath%>about.jsp"><img src="<%=basePath%>images/c2.gif" border=0 alt="系统简介"></a>
				<img src="<%=basePath%>images/c1.gif" border=0 alt="操作手册">
			</div>
			<br />
			<div id="main">
				<table border="0" align="center">
					<tr>
						<td>
							<%
								String message = (String) request.getAttribute("message");
								if (message != null) {
									out.print("<font color='red'>" + message + "</font>");
								}
							%>
							<form action="<%=basePath%>servlet/UserValid"
								method="post">
								用户名：
								<input type="text" name="username">
								<br>
								密&nbsp;&nbsp;&nbsp;码：
								<input type="password" name="password">
								<br>
								验证码：
								<input type="text" name="code">
								<img id="checkCode" src="<%=basePath%>imgcode.jsp" onclick="changeCode();" title="点击图片刷新验证码"/>
								<br>
								<input name="user" type="radio" value="0" checked>管理员
								<input name="user" type="radio" value="1">中介
								<br>
								<input type="submit" value="登录">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="取消">
								<br>
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>