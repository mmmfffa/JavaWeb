package com.mf.service;

import com.mf.dao.UserDao;
import com.mf.dao.UserDaoImpl;
import com.mf.pojo.Role;
import com.mf.pojo.User;
import com.mf.utils.UtilsJDBC_DBCP;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{
    //业务层调用dao层
    private UserDao userDao;
    public UserServiceImpl(){
        userDao=new UserDaoImpl();
    }
    @Override
    public User login(String userCode, String userName) throws SQLException {
        Connection conn = UtilsJDBC_DBCP.getConnection();
        User loginUser = userDao.getLoginUser(conn, userCode);
        UtilsJDBC_DBCP.releaseConnection(conn,null,null);
        return loginUser;
    }

    @Override
    public boolean updatePwd(int id, String password) throws SQLException {
//        System.out.println("newpassword" + password);
        Connection conn = UtilsJDBC_DBCP.getConnection();
        boolean flag=false;
        if(userDao.updatePwd(conn,id,password)>0) flag=true;
        UtilsJDBC_DBCP.releaseConnection(conn,null,null);
        return flag;
    }

    @Override
    public int getUserCount(String userName, int userRole) throws SQLException {
        Connection conn = UtilsJDBC_DBCP.getConnection();
        int count = userDao.getUserCount(conn, userName, userRole);
        UtilsJDBC_DBCP.releaseConnection(conn,null,null);
        return count;
    }

    @Override
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) throws Exception {
        Connection conn = UtilsJDBC_DBCP.getConnection();
        List<User> userList = userDao.getUserList(conn, queryUserName, queryUserRole, currentPageNo, pageSize);
        UtilsJDBC_DBCP.releaseConnection(conn,null,null);
        return userList;
    }

    @Test
    public void test() throws Exception {
        UserServiceImpl userService = new UserServiceImpl();
//        User login = userService.login("admin", "1234567");
//        System.out.println(login);
//        System.out.println(userService.getUserCount(null, 0));
//        List<User> userList = userService.getUserList(null, 0, 3, 3);
//        for (User user : userList) {
//            System.out.println(user);
//        }
    }
}
