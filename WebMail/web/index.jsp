<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>注册</title>
    <style>
      div{
        width: 300px;
        height: 260px;
        margin: 140px auto;
        opacity: 0.7;
        background: url("/image/1.png") 0 0 no-repeat;
      }
      p{
        margin-left: 30%;
      }
    </style>

  </head>
  <body>
    <div>
      <form action="${pageContext.request.contextPath}/RegisterServlet.do" method="post">
        <br>
        <p>用户名：<input type="text" name="username"></p>
        <p>密码：<input type="password" name="password"></p>
        <p>邮箱：<input type="text" name="email"></p>
        <p><input type="submit" value="注册"></p>
      </form>
    </div>
</body>
</html>
