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
	ArrayList<Agency> agencyList = (ArrayList<Agency>) request.getAttribute("agencyList");
	if (agencyList.size() == 0) {
		out.println("没有查询到信息！");
	} else {

		out.println("<center><table width='100%' border='1'>");
		out.println("<tr><td>照片</td><td>帐户信息</td><td>操作</td></tr>");
		for (int i = 0; i < agencyList.size(); i++) {
			Agency agency1= agencyList.get(i);
			out.println("<tr><td width=180><img src='"
							+basePath
							+"upload/"
							+ agency1.getPhoto()
							+"' width=180 height=140/>"
							+"</td><td>记录号ID："
							+ agency1.getAid()
							+ "<br>账号："
							+ agency1.getUsername()
							+ "<br>姓名："
							+ agency1.getAname()
							+ "<br>性别："
							+ agency1.getAsex()
							+ "</td><td><a href='"
							+ basePath
							+ "servlet/AdminServlet?para=preupdate&aid="
							+ agency1.getAid()
							+ "'><img src='"
							+ basePath
							+ "admin/images/edit.gif ' border='0' alt='修改'/></a>  <a href='"
							+ basePath
							+ "servlet/AdminServlet?para=delete&aid="
							+ agency1.getAid()
							+ "&username="
							+ request.getAttribute("condition")
							+ "' onclick='javascript:return del()'><img src='"
							+ basePath
							+ "admin/images/delete.gif ' border='0' alt='删除'/></a></td></tr>");
		}
		out.println("</table></center>");
	}
%>
<%@ include file="footer.jsp"%>