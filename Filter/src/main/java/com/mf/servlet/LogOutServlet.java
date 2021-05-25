package com.mf.servlet;

import com.mf.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object userSession = req.getSession().getAttribute(Constant.USER_SESSION);
        if(userSession!=null) req.getSession().removeAttribute(Constant.USER_SESSION);
        resp.sendRedirect(req.getContextPath()+"/login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
