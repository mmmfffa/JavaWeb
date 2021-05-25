<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--  内置对象--%>
<%
    pageContext.setAttribute("name1","刘邦1");//pageContext在当前页面有效
    request.setAttribute("name2","刘邦2");//只在一次请求中有效哦,请求转发携带
    session.setAttribute("name3","刘邦3");//一次会话会话中有效
    application.setAttribute("name4","刘邦4");//在服务器中有效
%>
<%--   脚本中代码原封不动放到java中 --%>
<%
    //通过pageContext来取值
    //从底层到高层(作用域)：pageContext-->request-->session-->application
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");
    String name4 = (String)  pageContext.findAttribute("name4");

%>

<%--使用el表达式输出--%>
<h2>取出的值为：</h2>
<h3>${name1}</h3>
<h3>${name2}</h3>
<h3>${name3}</h3>
<h3>${name4}</h3>

<%--
   ${name5}输出为空被过滤掉
   <%=name5%>输出null 不希望看到
--%>
</body>
</html>
