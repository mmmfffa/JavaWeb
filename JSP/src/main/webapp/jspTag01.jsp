<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jspTag01</title>
</head>
<body>
    <h2>jspTag01页面</h2>
    <% request.setCharacterEncoding("utf8"); %>
    <% response.setContentType("text/html;charset=UTF-8");%>
<%--    <jsp:include page="pageContext02.jsp"/>--%>
    <jsp:forward page="jspTag02.jsp">
        <jsp:param name="value1" value="值1"/>
        <jsp:param name="value2" value="值2"/>
    </jsp:forward>
</body>
</html>
