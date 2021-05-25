<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>文件上传</title>
<body>
<%--  通过表单上传文件
      get：大小有限制 post无限制
--%>
     <form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data"  method="post">
         <p>上传用户：<input type="text" name="username"></p>
         <P><input type="file" name="file1"></P>
         <P><input type="file" name="file2"></P>
         <P><input type="submit" value="上传"> | <input type="reset" value="重置"></P>
     </form>
</body>
</html>
