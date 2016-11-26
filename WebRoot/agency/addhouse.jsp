<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="header.jsp"%>
<script type="text/JavaScript" src="MyDatePicker.js">
<script language="javascript">
function alarm(){
		alert("只允许上传jpg,gif,bmp类型的文件");
}
function checkExt(){
		var allowArray = new Array("jpg","gif","bmp");
		var simg = document.getElementById("simg").value;
		var fileExt = simg.substr(simg.lastIndexOf(".")+1);
		var flag = false;
		for(var i = 0;i<allowArray.length;i++){
			if (allowArray[i] == fileExt.toLowerCase()){
				flag = true;
				break;
			}
		}
		if (flag == false){
			alert("不允许上传的文件类型，只允许上传jpg,gif,bmp类型的文件");
		}
		return flag;
	}
</script>
<form action="<%=basePath%>servlet/HouseServlet?para=insert" method="post" enctype="multipart/form-data" onSubmit="return checkExt();">
	<table width="298" border="0" align="center"
		cellpading="2"
		cellspacing="1">
		<tr>
			<td align="right">
				房主号：
			</td>
			<td align="left">
				<input type="text" name="cid">
			</td>
		</tr>
		<tr>
			<td align="right">
				地址：
			</td>
			<td>
				<input type="text" name="hadd">
			</td>
		</tr>
		<tr>
			<td align="right">
				面积：
			</td>
			<td align="left">
				<input type="text" name="harea">
			</td>
		</tr>

		<tr>
			<td align="right">
				月租：
			</td>
			<td align="left">
				<input type="text" name="hspend">
			</td>
		</tr>

		<tr>
			<td align="right">
				标题：
			</td>
			<td align="left">
				<input type="text" name="hremark">
			</td>
		</tr>
		
		<tr>
			<td align="right">
				状态：
			</td>
			<td>
				<select name="hstate">
			        <option value="未租">未租</option>
			        <option value="已租">已租</option>
 		                </select>
			</td>
		</tr>
		<tr>
			<td align="right">
				照片：
			</td>
			<td>
				<input type="file" id="simg" name="simg" onblur="return alarm();" >
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="提交">
				&nbsp;
				<input type="reset" value="取消">
			</td>
		</tr>
	</table>
</form>
<%@ include file="footer.jsp"%>