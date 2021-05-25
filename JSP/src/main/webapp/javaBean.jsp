<%@ page import="com.mf.pojo.Sc" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
    以前要使用一个类是这样new对象的 new Sc();
    set方法相当于
--%>
<jsp:useBean id="sc" class="com.mf.pojo.Sc" scope="page"/>
<jsp:setProperty name="sc" property="cno" value="222"/>
<jsp:setProperty name="sc" property="sno" value="2"/>
<jsp:setProperty name="sc" property="grade" value="92"/>
<jsp:setProperty name="sc" property="newGrade" value="A"/>

 课程号：   <jsp:getProperty name="sc" property="cno"/>
 学号：   <jsp:getProperty name="sc" property="sno"/>
 成绩：   <jsp:getProperty name="sc" property="grade"/>
 等级：   <jsp:getProperty name="sc" property="newGrade"/>

</body>
</html>
