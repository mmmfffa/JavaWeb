<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>coreWhen</title>
</head>
<body>
<%--set用来设置变量值--%>
    <c:set var="score" value="88"/>
    <c:choose>
        <c:when test="${score>=90}">哟秀！</c:when>
        <c:when test="${score>=80}">良好！</c:when>
        <c:when test="${score>=70}">不错！</c:when>
        <c:when test="${score>=60}">加油！</c:when>
    </c:choose>
</body>
</html>
