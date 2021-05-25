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
public class SessionTest02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //得到session
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        writer.println(session.getAttribute("name"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
