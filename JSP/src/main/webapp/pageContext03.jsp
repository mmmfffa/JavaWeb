<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/21
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <%
       pageContext.forward("/pageContext02.jsp");//页面中写法
       request.getRequestDispatcher("/pageContext02.jsp").forward(request,response);//后台写法
   %>
</body>
</html>
