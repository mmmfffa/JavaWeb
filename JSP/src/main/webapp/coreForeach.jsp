<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
     <%
         ArrayList<String>  people= new ArrayList<>();
         people.add("Mafu1");
         people.add("Mafu2");
         people.add("Mafu3");
         people.add("Mafu4");
         request.setAttribute("list",people);
     %>
     <c:forEach var="people" items="${list}">
         <c:out value="${people}"/>
         <br>
     </c:forEach>
<%--
var:每次遍历出来的变量
items:要遍历的集合
begin:开始
end：结束
step：步长
--%>
      <hr>
      <c:forEach begin="1" end="3" step="2" var="people" items="${list}">
          <c:out value="${people}"/>
          <br>
      </c:forEach>
</body>
</html>
