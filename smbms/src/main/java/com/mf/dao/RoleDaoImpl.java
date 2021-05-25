package com.mf.dao;

import com.mf.pojo.Role;
import com.mf.utils.UtilsJDBC_DBCP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{
    @Override
    public List<Role> getRoleList(Connection conn) throws SQLException {
        List<Role> roleList = new ArrayList<>();
        StringBuffer sql=new StringBuffer();
        sql.append("select * from smbms_role");
        if (conn==null) return null;
        Object[] params = {};
        System.out.println("UserServiceImpl:getUserCount-->"+sql.toString());//输出最后完整的sql
        ResultSet res = UtilsJDBC_DBCP.executeQuery(conn, sql.toString(), params);
        while (res.next()){
            Role role = new Role();
            role.setId(res.getInt("id"));
            role.setRoleCode(res.getString("roleCode"));
            role.setRoleName(res.getString("roleName"));
            roleList.add(role);
        }
        UtilsJDBC_DBCP.releaseConnection(null,null,res);//conn在业务层关闭
        return roleList;
    }
}
