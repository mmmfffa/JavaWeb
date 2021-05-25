<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Listener</title>
    <style>
         span{
           color: aqua;
         }
    </style>
  </head>
  <body>
    <h2>
      当前在线人数：
      <span><%= this.getServletConfig().getServletContext().getAttribute("onlineCount")%></span>
    </h2>
  </body>
</html>
