<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page errorPage="error/500.jsp" %>--%>
<html>
<head>
    <title>JSP指令</title>
</head>
<body>
    <%@ include file="common/header.jsp"%>
    <h1>项目主体1</h1>
    <%@ include file="common/footer.jsp"%>
    <hr>
    <jsp:include page="/common/header.jsp"/>
    <h1>项目主体2</h1>
    <jsp:include page="/common/footer.jsp"/>
</body>
</html>
