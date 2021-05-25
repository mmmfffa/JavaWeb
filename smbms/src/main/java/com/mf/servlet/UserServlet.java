package com.mf.servlet;

import com.alibaba.fastjson.JSONArray;
import com.mf.pojo.Role;
import com.mf.pojo.User;
import com.mf.service.RoleServiceImpl;
import com.mf.service.UserService;
import com.mf.service.UserServiceImpl;
import com.mf.utils.Constant;
import com.mf.utils.PageSupport;
import com.mysql.jdbc.StringUtils;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {
    //实现servlet复用
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method!=null&&method.equals("savepwd")) updatePwd(req,resp);
        else if(method!=null&&method.equals("pwdmodify")) pwdModify(req,resp);
        else if(method!=null&&method.equals("query")) query(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserServlet...start....");
        //从session拿id
        Object user = req.getSession().getAttribute(Constant.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
//        System.out.println("newpassword" + newpassword);
        if(user!=null&& !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            boolean b = false;
            try {
                b = userService.updatePwd(((User) user).getId(), newpassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(b) {
                System.out.println("修改成功");
                req.setAttribute(Constant.MESSAGE, "修改成功!请重新登陆！");
                //移除session
                req.getSession().removeAttribute(Constant.USER_SESSION);
                System.out.println("UserServlet:"+req.getSession().getAttribute(Constant.USER_SESSION));
            }
            else req.setAttribute(Constant.MESSAGE,"修改失败!");
        }else {
            req.setAttribute(Constant.MESSAGE,"新密码无效!");
        }
        System.out.println("转发页面");
//        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
        resp.sendRedirect(req.getContextPath()+"/jsp/pwdmodify.jsp");
    }
    //验证旧密码,session中有用户密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object user = req.getSession().getAttribute(Constant.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        //万能的Map
        Map<String, String> resultMap = new HashMap<>();
        if(user==null){//session过期
           resultMap.put("result","sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){
            resultMap.put("result","error");
        }else {
            String userPassword = ((User) user).getUserPassword();
            if(oldpassword.equals(userPassword)){
                resultMap.put("result","true");
            }else {
                resultMap.put("result","false");
            }
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        //JSONArray阿里巴巴的json工具类转换格式
        /*
           resultMap=["result","sessionerror","result","error"]
           json格式={k,v}
        * */
        writer.write(JSONArray.toJSONString(resultMap));
        //由于是流所以需要刷新和关闭
        writer.flush();
        writer.close();
    }
    //重点+难点
    public void query(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //查询用户列表
        //从前端获取数据
        String queryUserName = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole=0;

        //获取用户列表
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList=null;
        //第一次走请求，是第一页
        int pageSize=5;//页面大小固定，可以写到配置文件中
        int currentPageNo=1;

        if(queryUserName==null) queryUserName="";
        if(temp!=null&&!temp.equals("")) queryUserRole=Integer.parseInt(temp);
        if(pageIndex!=null) currentPageNo = Integer.parseInt(pageIndex);
        //获取用户总数:分页，上一页，下一页
        int totalCount = userService.getUserCount(queryUserName, queryUserRole);
        //总页数工具类
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalPageCount(totalCount);
        int totalPageCount =-1;
        if(totalCount%pageSize==0 ) totalPageCount =totalCount/pageSize;
        else totalPageCount =totalCount/pageSize+1;
        //控制首页和尾页
        if(currentPageNo<1) currentPageNo=1;
        if(currentPageNo>totalPageCount) currentPageNo=totalPageCount;

        //获取用户列表展示
        userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
        req.setAttribute("userList",userList);
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        req.setAttribute("roleList",roleList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);
        req.setAttribute("queryUserName",queryUserName);
        req.setAttribute("queryUserRole",queryUserRole);


        //返回前端
        req.getRequestDispatcher("userlist.jsp").forward(req,resp);
    }
}
