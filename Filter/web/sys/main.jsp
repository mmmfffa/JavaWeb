<%@ page import="com.mf.utils.Constant" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/22
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <style>
        a{
            text-decoration: none;
            color: deepskyblue;
        }
    </style>
</head>
<body>
<%--  <%--%>
<%--      Object userSession = request.getSession().getAttribute(Constant.USER_SESSION);--%>
<%--      if(userSession==null) response.sendRedirect(request.getContextPath()+"/login.jsp");--%>
<%--  %>--%>
  <h2>主页</h2>
  <div>
      <a href="${pageContext.request.contextPath}/servlet/logout">注销</a>
  </div>
</body>
</html>
