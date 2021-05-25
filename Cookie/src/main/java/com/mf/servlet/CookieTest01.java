package com.mf.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author mf
 * @create 2021-05-19-20:12
 */
//保存用户上一次访问的时间
public class CookieTest01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器，告诉你，你来的时间，把这个时间封装成一个信件，你下次带着来，就知道你来了
        //解决中文乱码
        req.setCharacterEncoding("UTF-8");

        //注意响应如果不走jsp的话还要加
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();
        //服务器从客户端获取Cookie
        Cookie[] cookies = req.getCookies();
        //判断Cookie是否存在
        if(cookies!=null){
            out.println("欢迎回来！");
            out.print("你上一次访问的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(cookie.getName().equals("lastLoginTime")){
                    long lastLoginTime = Long.parseLong(cookie.getValue());
                    out.println(new Date(lastLoginTime).toLocaleString());
                }

            }
        }else {
            out.println("欢迎注册!");
        }
        //服务端可客户端响应一个Cookie
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis()+"");
        //cookie有效期为一天
        cookie.setMaxAge(24*60*60);
        resp.addCookie(cookie);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
