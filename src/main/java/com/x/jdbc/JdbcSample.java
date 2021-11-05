package com.x.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * create by 许庆之 on 2020/3/12.
 */
public class JdbcSample {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/xqz?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String username = "root";
        String password = "";
        Connection cn = DriverManager.getConnection(url,username,password);*/
        Connection cn = JdbcUtils.getConnection();
        Statement st = cn.createStatement();
        String sql = "select * from role";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        System.out.println(rs.getString(1));
        rs.close();
        st.close();
        cn.close();
    }

}
