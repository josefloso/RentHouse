<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="header.jsp"%>
<form method="post" action="<%=basePath%>servlet/HouseServlet?para=search">
	<p>
		房屋信息：
		<input type="text" maxlength="6" size="6" name="hremark">
		<br>
	</p>
	<p>
		&nbsp;
		<input type="submit" value="查询" name="button1">
		<input type="reset" value="重填" name="button2">
		<br>
		<br>
	</p>
</form>
<%@ include file="footer.jsp"%>
