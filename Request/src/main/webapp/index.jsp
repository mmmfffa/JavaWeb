<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>登录</title>
    <style>
         h2{
             margin-top: 61px;
             color: #b9ee84;
             position: relative;
             left: 10%;
         }
         div{
             width: 400px;
             height: 110px;
             background: #ec9cad;
             opacity: 0.4;
             border-radius: 10px;
             text-align: center;
             position: relative;
             left: 10%;
         }
    </style>
<body>
   <h2>登录</h2>
   <div>
       <form action="${pageContext.request.contextPath}/login" method="post">
           <label>用户名：</label>
           <input type="text" name="username">
           <br>
           <label>密码：</label>
           <input type="password" name="password">
           <br>
           <label>技能：</label>
           <input type="checkbox" name="hobby" value="九阳神功">九阳神功
           <input type="checkbox" name="hobby" value="幽冥神掌">幽冥神掌
           <input type="checkbox" name="hobby" value="大挪移">大挪移
           <br>
           <input type="submit" value="提交">
       </form>
   </div>
</body>
</html>
