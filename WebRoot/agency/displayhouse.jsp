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
	ArrayList<House> houseList = (ArrayList<House>) request.getAttribute("houseList");
	if (houseList.size() == 0) {
		out.println("没有查询到信息！");
	} else {

		out.println("<center><table width='100%' border='1'>");
		out.println("<tr><td>照片</td><td>帐户信息</td><td>操作</td></tr>");
		for (int i = 0; i < houseList.size(); i++) {
			House house1= houseList.get(i);
			out.println("<tr><td width=180><img src='"
							+basePath
							+"upload/"
							+ house1.getHphoto()
							+"' width=180 height=140/>"
							+"</td><td>记录号ID："
							+ house1.getHid()
							+ "<br>标题："
							+ house1.getHremark()
							+ "<br>房主号："
							+ house1.getCid()
							+ "<br>地址："
							+ house1.getHadd()
							+ "<br>月租："
							+ house1.getHspend()
							+ "<br>面积："
							+ house1.getHarea()
							+ "<br>状态："
							+ house1.getHstate()
							
							+ "</td><td><a href='"
							+ basePath
							+ "servlet/HouseServlet?para=preupdate&hid="
							+ house1.getHid()
							+ "'><img src='"
							+ basePath
							+ "agency/images/edit.gif ' border='0' alt='修改'/></a> <a href='"
							+ basePath
							+ "servlet/HouseServlet?para=addshopcar&hid="
							+ house1.getHid()
							+ "'><img src='"
							+ basePath
							+ "agency/images/add.gif ' border='0' alt='添加到购物车'/></a> <a href='"
							+ basePath
							+ "servlet/HouseServlet?para=delete&hid="
							+ house1.getHid()
							+ "&hremark="
							+ request.getAttribute("condition")
							+ "' onclick='javascript:return del()'><img src='"
							+ basePath
							+ "agency/images/delete.gif ' border='0' alt='删除'/></a></td></tr>");
		}
		out.println("</table></center>");
	}
%>
<%@ include file="footer.jsp"%>