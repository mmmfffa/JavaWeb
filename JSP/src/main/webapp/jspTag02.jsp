<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jspTag02</title>
</head>
<body>
    <h2>JSP页面转发</h2>
    <% request.setCharacterEncoding("utf8"); %>
    <% response.setContentType("text/html;charset=UTF-8");%>
    <label>参数1：</label> <%=request.getParameter("value1")%>
    <label>参数2：</label> <%=request.getParameter("value2")%>
</body>
</html>
