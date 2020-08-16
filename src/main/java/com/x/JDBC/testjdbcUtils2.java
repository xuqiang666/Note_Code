package com.x.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * create by 许庆之 on 2020/3/12.
 */
public class testjdbcUtils2 {

    public static void main(String[] args) {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        List<Role> rowList = null;
        try {
            cn = jdbcUtils.getConnection();
            st = cn.createStatement();
            String sql = "select * from role";
            rs = st.executeQuery(sql);
            //System.out.println(rs.next());

            Role role = new Role();
            rowList = new ArrayList<Role>();
            while(rs.next()){
                String id = rs.getString("id");
                String roleName = rs.getString("roleName");
                String roleDesc = rs.getString("roleDesc");
                role.setId(id);
                role.setRoleName(roleName);
                role.setRoleDesc(roleDesc);
                rowList.add(role);
                }
            System.out.println(rowList.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {


            jdbcUtils.close(rs,st,cn);
        }


    }
}
