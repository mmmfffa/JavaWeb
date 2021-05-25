<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <h3>if测试</h3>
   <form action="coreIf.jsp" method="get">
<%--    获取表单中的数据：${param.参数名}--%>
       <input type="text" name="username" value="${param.username}">
       <input type="submit" value="提交">
   </form>
<%--判断如果提交的用户名是管理员则登录成功--%>
<%--  var是返回值--%>
<c:if test="${param.username=='admin'}" var="isAdmin">
    <c:out value="欢迎Admin登录！"/>
</c:if>
<c:out value="${isAdmin}"/>
</body>
</html>
