package com.mf.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import static java.sql.DriverManager.println;

/**
 * @author mf
 * @create 2021-05-19-21:22
 */
//注销session
public class SessionTest03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到session
        HttpSession session = req.getSession();
        session.removeAttribute("name");
        session.invalidate();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
