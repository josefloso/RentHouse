<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*,com.cuc.model.*,com.cuc.dao.*,java.util.*"%>
<%@ include file="header.jsp"%>
<script type="text/javascript">
	function del() {
		var msg = "您真的确定要删除吗？\n\n请确认！";
		if (confirm(msg) == true) {
			return true;
		} else {
			return false;
		}
	}
</script>

<%
	ArrayList<Customer> customerList = (ArrayList<Customer>) request.getAttribute("customerList");
	if (customerList.size() == 0) {
		out.println("没有查询到信息！");
	} else {

		out.println("<center><table width='100%' border='1'>");
		out.println("<tr><td>照片</td><td>帐户信息</td><td>操作</td></tr>");
		for (int i = 0; i < customerList.size(); i++) {
			Customer customer1= customerList.get(i);
			out.println("<tr><td width=180><img src='"
							+basePath
							+"upload/"
							+ customer1.getPhoto()
							+"' width=180 height=140/>"
							+"</td><td>记录号ID："
							+ customer1.getCid()
							+ "<br>姓名："
							+ customer1.getCname()
							+ "<br>电话："
							+ customer1.getCtel()
							+ "<br>年龄："
							+ customer1.getCage()
							+ "<br>性别："
							+ customer1.getCsex()
							+ "</td><td><a href='"
							+ basePath
							+ "servlet/CustomerServlet?para=preupdate&cid="
							+ customer1.getCid()
							+ "'><img src='"
							+ basePath
							+ "agency/images/edit.gif ' border='0' alt='修改'/></a>  <a href='"
							+ basePath
							+ "servlet/CustomerServlet?para=delete&cid="
							+ customer1.getCid()
							+ "&cname="
							+ request.getAttribute("condition")
							+ "' onclick='javascript:return del()'><img src='"
							+ basePath
							+ "agency/images/delete.gif ' border='0' alt='删除'/></a></td></tr>");
		}
		out.println("</table></center>");
	}
%>
<%@ include file="footer.jsp"%>