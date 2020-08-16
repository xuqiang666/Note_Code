package com.x.JDBC;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * create by 许庆之 on 2020/3/12.
 * 一般工具类的方法都是静态的方便调用
 */
public class jdbcUtils {

    private static String url;
    private static String username;
    private static String password;
    private static String driver;

    /*不想传递参数，又要保证工具类的通用性，使用配置文件*/
    static{
        try {
            //读取资源文件获取值
            //1.创建Properties集合类
            Properties pro = new Properties();
            //获取src路径下文件的方式，----》ClassLoader 类加载器
            ClassLoader classLoader = jdbcUtils.class.getClassLoader();
            //获取文件的绝对路径
            URL res = classLoader.getResource("db.properties");
            //获取路径的字符串
            String path = res.getPath();
            System.out.println(path);
            //2.加载文件
            pro.load(new FileReader(path));
            //3.属性赋值
            url = pro.getProperty("db.url");
            username = pro.getProperty("db.username");
            password = pro.getProperty("db.password");
            driver = pro.getProperty("db.driverClass");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(ResultSet rs,Statement st,Connection cn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(cn!=null){
            try {
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement st,Connection cn){
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(cn!=null){
            try {
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
