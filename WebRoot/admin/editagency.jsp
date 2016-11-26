<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*,com.cuc.model.*"%>
<%@ include file="header.jsp"%>
<script type="text/JavaScript" src="MyDatePicker.js">
<script language="javascript" >
function previewImg(filepath){
	
	var image1 = document.getElementById("image1");
		image1.style.display = "block";
		image1.src = filepath;
		
	}
</script>
<%
   Agency agency1=(Agency)request.getAttribute("agency");

 %>
<form action="<%=basePath%>servlet/AdminServlet?para=update" method="post"  enctype="multipart/form-data">
<input type="hidden" name="aid" value=<%=agency1.getAid() %>>	
<table width="298" border="0" align="center"
		cellpading="2"
		cellspacing="1">
		<tr>
			<td align="right">
				用户名：
			</td>
			<td align="left">
				<input type="text" name="username" value=<%=agency1.getUsername() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				密&nbsp;&nbsp;码：
			</td>
			<td>
				<input type="password" name="password" value=<%=agency1.getPassword() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				真实姓名：
			</td>
			<td align="left">
				<input type="text" name="aname" value=<%=agency1.getAname() %>>
			</td>
		</tr>
			<td align="right">
				性别：
			</td>
			<td>
              <select name="asex">
<% 
    String asex=agency1.getAsex();
    if(asex.equals("男")){
%>
 			        <option value="男" checked>男</option>
			        <option value="女">女</option>
<%    }else{ %>
 			        <option value="男">男</option>
			        <option value="女" checked>女</option>
<%
    }
%>
 		      </select>				

			</td>
		</tr>
		<tr>
			<td align="right">
				照片：
			</td>
			<td>
				<img id="image1" src="<%=basePath%>upload/<%=agency1.getPhoto()%>" width="180" height="140"/>
				<input type="hidden" name="oldphoto" value=<%=agency1.getPhoto() %>>
			</td>
		</tr>

		<tr>
			<td align="right">
				修改照片：
			</td>
			<td>
				<input type="file" id="simg" name="simg"  onchange="previewImg(this.value);"/> 
			</td>
		</tr>

		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="更新">
				&nbsp;
				<input type="reset" value="取消">
			</td>
		</tr>
	</table>
</form>
<%@ include file="footer.jsp"%>