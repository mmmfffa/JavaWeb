package com.mf.dao;

import com.mf.pojo.Role;
import com.mf.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //得到登录的用户
    public User getLoginUser(Connection conn, String userCode) throws SQLException;
    //修改用户密码
    public int updatePwd(Connection conn,int id,String password) throws SQLException;
    //根据用户名或者角色，查询用户总数
    public int getUserCount(Connection conn,String userName,int userRole) throws SQLException;
    //h获取用户列表
    public List<User> getUserList(Connection conn, String userName, int userRole, int currentPageNo, int pageSize) throws Exception;

}
