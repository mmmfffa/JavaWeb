package com.mf.service;

import com.mf.pojo.Role;
import com.mf.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    //用户登录
    public User login(String userCode,String userName) throws SQLException;
    //根据用户id修改密码
    public boolean updatePwd(int id, String password) throws SQLException;
    //查询记录数
    public int getUserCount(String userName, int userRole) throws SQLException;
    //查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) throws Exception;

}
