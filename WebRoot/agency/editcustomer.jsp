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
   Customer customer1=(Customer)request.getAttribute("customer");

 %>
<form action="<%=basePath%>servlet/CustomerServlet?para=update" method="post"  enctype="multipart/form-data">
<input type="hidden" name="cid" value=<%=customer1.getCid() %>>	
<table width="298" border="0" align="center"
		cellpading="2"
		cellspacing="1">
		<tr>
			<td align="right">
				姓名：
			</td>
			<td align="left">
				<input type="text" name="cname" value=<%=customer1.getCname() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				电话：
			</td>
			<td>
				<input type="text" name="ctel" value=<%=customer1.getCtel() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				年龄：
			</td>
			<td align="left">
				<input type="text" name="cage" value=<%=customer1.getCage() %>>
			</td>
		</tr>
			<td align="right">
				性别：
			</td>
			<td>
              <select name="csex">
<% 
    String csex=customer1.getCsex();
    if(csex.equals("男")){
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
				<img id="image1" src="<%=basePath%>upload/<%=customer1.getPhoto()%>" width="180" height="140"/>
				<input type="hidden" name="oldphoto" value=<%=customer1.getPhoto() %>>
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