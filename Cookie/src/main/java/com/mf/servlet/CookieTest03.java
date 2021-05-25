package com.mf.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author mf
 * @create 2021-05-19-20:12
 */
//中文参数传递
public class CookieTest03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                if(cookie.getName().equals("name")){
                    out.println(URLDecoder.decode(cookie.getValue(),"utf-8"));
                }

            }
        }else {
            out.println("欢迎注册!");
        }
        Cookie cookie = new Cookie("name", URLEncoder.encode("韩信","utf-8"));
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
