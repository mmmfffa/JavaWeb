package com.mf.servlet;

import com.mf.pojo.User;
import com.mf.utils.SendMail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户请求封装成对象
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        User user = new User(username, password, email);
        //专门用线程发送邮件防止出现耗时
        SendMail send = new SendMail(user);
        send.start();
        req.setAttribute("message","注册成功，查看邮箱接收信息，若网络不稳定可能过会才收到！");
        req.getRequestDispatcher("info.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
