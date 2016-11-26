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
   House house1=(House)request.getAttribute("house");

 %>
<form action="<%=basePath%>servlet/HouseServlet?para=update" method="post"  enctype="multipart/form-data">
<input type="hidden" name="hid" value=<%=house1.getHid() %>>	
<table width="298" border="0" align="center"
		cellpading="2"
		cellspacing="1">
		<tr>
			<td align="right">
				房主号：
			</td>
			<td align="left">
				<input type="text" name="cid" value=<%=house1.getCid() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				标题：
			</td>
			<td>
				<input type="text" name="hremark" value=<%=house1.getHremark() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				地址：
			</td>
			<td align="left">
				<input type="text" name="hadd" value=<%=house1.getHadd() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				面积：
			</td>
			<td align="left">
				<input type="text" name="harea" value=<%=house1.getHarea() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				租金：
			</td>
			<td align="left">
				<input type="text" name="hspend" value=<%=house1.getHspend() %>>
			</td>
		</tr>
			<td align="right">
				状态：
			</td>
			<td>
              <select name="hstate">
<% 
    String hstate=house1.getHstate();
    if(hstate.equals("未租")){
%>
 			        <option value="未租" checked>未租</option>
			        <option value="已租">已租</option>
<%    }else{ %>
 			        <option value="未租">未租</option>
			        <option value="已租" checked>已租</option>
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
				<img id="image1" src="<%=basePath%>upload/<%=house1.getHphoto()%>" width="180" height="140"/>
				<input type="hidden" name="oldphoto" value=<%=house1.getHphoto() %>>
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