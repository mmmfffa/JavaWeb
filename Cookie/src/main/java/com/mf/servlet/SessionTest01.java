package com.mf.servlet;

import com.mf.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import static java.sql.DriverManager.println;

/**
 * @author mf
 * @create 2021-05-19-21:22
 */
public class SessionTest01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //得到session
        HttpSession session = req.getSession();
        //给session存东西
        session.setAttribute("name",new Person("刘稚",18,"女"));
        //获取session的id
        String id = session.getId();
        //判断是否新创建session
        PrintWriter writer = resp.getWriter();
        if(session.isNew()){
            writer.println("欢迎注册,ID为："+id);
        }else {
            writer.println("欢迎登录,ID为："+id);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
