<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	
	//以下代码是页面保护代码
	//清除过期缓存
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache");
    //判断是否正常登录过，未登录过则取不到admin值
    com.cuc.model.Admin admin = (com.cuc.model.Admin) session.getAttribute("admin");
	if (admin == null) {
		response.sendRedirect("../login.jsp");
		return;
	}
%>


<HTML>
<HEAD>
<TITLE></TITLE>
<LINK REL=stylesheet HREF="script/toc.css" TYPE="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script/AdminTree.js"></script>
<STYLE TYPE='text/css'>
.level1 {margin-left:30;}
.level2 {display:none;margin-left:38;}
</STYLE>
</HEAD>
<BODY onload="init()" topmargin="0" leftmargin="0" rightmargin="0">
<br><br>
<h4>系统菜单</h4><hr>
<DIV CLASS="level1" ID='head1Parent'>
	<A class=OUTDENT href="" onclick='return expandIt("head1");'><IMG border=0 name=imEx  src="images/arrowUp.gif" id=ttt>中介信息管理</a>
</DIV>

<DIV CLASS="level2" ID='head1Child'>
	<A href="addagency.jsp" target="right" id=ttt onclick="doClick()"><LI>添加中介</LI></a>
	<A href="searchagency.jsp" target="right" id=ttt ><LI>查询中介</LI></a>
</DIV>




<DIV CLASS="level1" ID='head7Parent'>
               <A class=OUTDENT href="<%=basePath%>servlet/AdminServlet?para=logout" target=_top><IMG border=0 height=13 name=imEx  src="images/arrowDn.gif" width=17>退出系统</a>
</DIV>
</BODY>
</html>
