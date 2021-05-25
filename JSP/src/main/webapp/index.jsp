<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>JSP基础语法</title>
<style>
    .div-1{
        width: 500px;
        height: 304px;
        position: relative;
        top: 3%;
        left: 15%;
        background: #0affa5;
        opacity: 0.3;
    }
    .div-2 {
        width: 400px;
        height: 40px;
        text-align: center;
        position: relative;
        left: 10%;
        background: #ff4848;
        top: 5%;
    }
    h3{
        font-size: 18px;
        line-height: 40px;
        font-family: 华文宋体, serif;
    }
</style>
<body>
<%--JSP表达式:用来将程序输出到客户端--%>
<%=
  new Date().toLocaleString()
%>
<hr>
<%--JSP脚本片段--%>
<%
    int sum=0;
    for (int i = 0; i < 100; i++) {
        sum+=i;
    }
    out.println("<h2>sum="+sum+"</h2>");
%>
<hr>
<%--嵌入HTML元素--%>
<div class="div-1">
<%
    int index=0;
    for (index = 0; index < 5; index++) {
%>
<div class="div-2">
    <h3>现在是第： <%= index%> 进行笔试</h3>
</div>
<%
    }
%>
<hr>
<%--全局变量--%>
<%!
   static {
       System.out.println("正在加载servlet");
   }
   private int globalVar=0;
   public void jspMafu(){
       System.out.println("进入了Mafu方法");
   }
%>
</div>
</body>
</html>
