package com.mf.service;

import com.mf.dao.RoleDao;
import com.mf.dao.RoleDaoImpl;
import com.mf.pojo.Role;
import com.mf.utils.UtilsJDBC_DBCP;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService{

    //业务层调用dao层
    private RoleDao roleDao;
    public RoleServiceImpl(){
        roleDao=new RoleDaoImpl();
    }
    @Override
    public List<Role> getRoleList() throws SQLException {
        Connection conn = UtilsJDBC_DBCP.getConnection();
        List<Role> roleList = roleDao.getRoleList(conn);
        UtilsJDBC_DBCP.releaseConnection(conn,null,null);
        return roleList;
    }
    @Test
    public void test() throws Exception {
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        System.out.println(roleList);
    }
}
