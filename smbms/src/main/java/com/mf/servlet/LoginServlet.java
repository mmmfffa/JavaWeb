package com.mf.servlet;

import com.mf.pojo.User;
import com.mf.service.UserService;
import com.mf.service.UserServiceImpl;
import com.mf.utils.Constant;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    //servlet控制层调用service层
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet...start....");
        //获取用户密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        //对比
        UserService userService = new UserServiceImpl();
        User login = userService.login(userCode, userPassword);
        if(login!=null){
            //将用户信息放到session中
           req.getSession().setAttribute(Constant.USER_SESSION,login);
           resp.sendRedirect(req.getContextPath()+"/jsp/frame.jsp");
        }else {
            req.setAttribute("error","输入用户名或密码有误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
