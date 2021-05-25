package com.mf.servlet;

import com.mf.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        if(username.equals("admin")&&password.equals("123456")){
            System.out.println("进入正确页面");
            req.getSession().setAttribute(Constant.USER_SESSION,req.getSession().getId());
            resp.sendRedirect(req.getContextPath()+"/sys/main.jsp");
        }else {
            System.out.println("进入错误页面");
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
