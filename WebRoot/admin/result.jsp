<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="header.jsp"%>

<font color=red><%=request.getAttribute("message") %></font>

<%@ include file="footer.jsp"%>
<SCRIPT LANGUAGE='JavaScript'>alert('<%=request.getAttribute("message")%>');javascript: history.back(-1);
</SCRIPT>