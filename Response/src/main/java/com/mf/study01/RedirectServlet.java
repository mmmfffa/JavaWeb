package com.mf.study01;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mf
 * @create 2021-05-19-17:01
 */
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*          resp.setHeader("Location","/Servlet03_war/Image");
          resp.setStatus(HttpServletResponse.SC_FOUND);//302*/
           //需要添加项目名字
          resp.sendRedirect("/Servlet03_war/Image");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
