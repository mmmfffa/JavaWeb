package com.mf.dao;

import com.mf.pojo.Role;
import com.mf.pojo.User;
import com.mf.utils.UtilsJDBC_DBCP;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public User getLoginUser(Connection conn, String userCode) throws SQLException {
        String sql="select * from smbms_user where userCode=?";
        if (conn==null) return null;
        PreparedStatement st = conn.prepareStatement(sql);
        Object[] params={userCode};
        ResultSet res = UtilsJDBC_DBCP.executeQuery(conn, sql, params);
        User user=null;
        if (res.next()){
            user=new User();
            user.setId(res.getInt("id"));
            user.setId(res.getInt("id"));
            user.setUserCode(res.getString("userCode"));
            user.setUserName(res.getString("userName"));
            user.setUserPassword(res.getString("userPassword"));
            user.setGender(res.getInt("gender"));
            user.setBirthday(res.getDate("birthday"));
            user.setPhone(res.getString("phone"));
            user.setAddress(res.getString("address"));
            user.setUserRole(res.getInt("userRole"));
            user.setCreatedBy(res.getInt("createdBy"));
            user.setCreationDate(res.getTimestamp("creationDate"));
            user.setModifyBy(res.getInt("modifyBy"));
            user.setModifyDate(res.getTimestamp("modifyDate"));
        }
        UtilsJDBC_DBCP.releaseConnection(null,st,res);//数据库连接先不用关
        return user;
    }

    @Override
    public int updatePwd(Connection conn, int id, String password) throws SQLException {
//        System.out.println("newpassword" + password);
        String sql="update smbms_user set userPassword=? where id=?";
        if (conn==null) return -1;
        PreparedStatement st = conn.prepareStatement(sql);
        Object[] params={password,id};
        int i = UtilsJDBC_DBCP.executeUpdate(conn, sql, params);
        UtilsJDBC_DBCP.releaseConnection(null,st,null);
        return i;
    }

    //项目难点
    @Override
    public int getUserCount(Connection conn, String userName, int userRole) throws SQLException {
        StringBuffer sql=new StringBuffer();
        sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole=r.id");
        if (conn==null) return -1;
        //list存放参数
        List<Object> lists = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(userName)){//拼接查询
           sql.append(" and u.userName like ?");
           lists.add("%"+userName+"%");//通配符
        }
        if (userRole>0){
           sql.append(" and u.userRole =?");
           lists.add(userRole);
        }
        //如何把list转换成数组
        Object[] params = lists.toArray();
        System.out.println("UserServiceImpl:getUserCount-->"+sql.toString());//输出最后完整的sql
        ResultSet res = UtilsJDBC_DBCP.executeQuery(conn, sql.toString(), params);
        int count =0;
        if(res.next()){
            count = res.getInt("count");
        }
        UtilsJDBC_DBCP.releaseConnection(null,null,res);//conn在业务层关闭
        return count;
    }

    @Override
    public List<User> getUserList(Connection conn, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        List<User> userList = new ArrayList<>();
        StringBuffer sql=new StringBuffer();
        sql.append("select u.*,r.roleName as userRoleName\n" +
                   "from smbms_user u,smbms_role r where\n" +
                   "u.userRole=r.id\n");
        if (conn==null) return null;
        //list存放参数
        List<Object> lists = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(userName)){//拼接查询
            sql.append("and u.userName like ?\n");
            lists.add("%"+userName+"%");//通配符
        }
        if (userRole>0){
            sql.append("and u.userRole =?\n");
            lists.add(userRole);
        }
        //在mysql数据库中，分页使用 limit startIndex，pageSize ; 总数
        sql.append("order by creationDate desc \n" +
                   "limit ?,?\n");
        currentPageNo=(currentPageNo-1)*pageSize;
        lists.add(currentPageNo);
        lists.add(pageSize);

        //如何把list转换成数组
        Object[] params = lists.toArray();
        System.out.println(Arrays.toString(params));
        System.out.println("UserServiceImpl:getUserList-->"+sql.toString());//输出最后完整的sql
        ResultSet res = UtilsJDBC_DBCP.executeQuery(conn, sql.toString(), params);
        while (res.next()){
            User user = new User();
            user.setId(res.getInt("id"));
            user.setUserCode(res.getString("userCode"));
            user.setUserName(res.getString("userName"));
            user.setGender(res.getInt("gender"));
            user.setBirthday(res.getDate("birthday"));
            user.setPhone(res.getString("phone"));
            user.setUserRole(res.getInt("userRole"));
            user.setUserRoleName(res.getString("userRoleName"));
            userList.add(user);
//            System.out.println("UserDaoImpl:getUserList-->"+user);
        }
        UtilsJDBC_DBCP.releaseConnection(null,null,res);//conn在业务层关闭

        return userList;
    }
}
