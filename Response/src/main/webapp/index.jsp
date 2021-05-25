<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
     <form action="${pageContext.request.contextPath}/Request" method="get">
         <label>用户名：</label>
         <input type="text" name="username">
         <br>
         <label>密码：</label>
         <input type="password" name="password">
         <br>
         <input type="submit" value="提交">
     </form>
</body>
</html>
